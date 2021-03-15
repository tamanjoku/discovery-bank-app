package za.co.discovery.bank.app.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.discovery.bank.app.dao.AtmDao;
import za.co.discovery.bank.app.dao.ClientAccountDao;
import za.co.discovery.bank.app.dao.CurrencyConversionRateDao;
import za.co.discovery.bank.app.data.model.Atm;
import za.co.discovery.bank.app.data.model.AtmAllocation;
import za.co.discovery.bank.app.data.model.ClientAccount;
import za.co.discovery.bank.app.data.model.CurrencyConversionRate;
import za.co.discovery.bank.app.data.model.Denomination;
import za.co.discovery.bank.app.data.model.dto.Conversion;
import za.co.discovery.bank.app.enums.AccountTypeCodeEnum;
import za.co.discovery.bank.app.enums.DenominationTypeCodeEnum;
import za.co.discovery.bank.app.enums.ResponseEnum;
import za.co.discovery.bank.app.exception.AtmNotFundedException;
import za.co.discovery.bank.app.exception.DataNotFoundException;
import za.co.discovery.bank.app.exception.InvalidWithdrawalAccountException;
import za.co.discovery.bank.app.exception.LimitExceededException;
import za.co.discovery.bank.app.service.ClientAccountService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Default Implementation of ClientAccountService interface.
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
@Service("clientAccountService")
public class ClientAccountServiceImpl implements ClientAccountService {

    private static final Locale locale = Locale.UK;

    private final ClientAccountDao clientAccountDao;
    private final CurrencyConversionRateDao currencyConversionRateDao;
    private final AtmDao atmDao;
    private final MessageSource messageSource;

    /**
     * Used by spring to instantiate a bean of this class autowiring by argument
     * resolvers the parameters in this constructor.
     */
    public ClientAccountServiceImpl(
            final ClientAccountDao clientAccountDao,
            final CurrencyConversionRateDao currencyConversionRateDao,
            final AtmDao atmDao,
            final MessageSource messageSource
    ) {
        this.clientAccountDao = clientAccountDao;
        this.currencyConversionRateDao = currencyConversionRateDao;
        this.atmDao = atmDao;
        this.messageSource = messageSource;
    }

    private List<ClientAccount> getClientAccounts(Integer clientId, boolean isTransactional ) throws DataNotFoundException {
        List<ClientAccount> accounts = clientAccountDao.getAllClientAccounts(clientId, isTransactional);
        if (accounts == null || accounts.isEmpty()) {
            throw new DataNotFoundException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_110.getLabelKey(), null, locale),
                    ResponseEnum.RESPONSE_CODE_110
            );
        }
        return accounts;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientAccount> getTransactionalClientAccounts(Integer clientId) throws DataNotFoundException {
        return getClientAccounts(clientId, true);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<ClientAccount, Conversion> getCurrencyClientAccounts(Integer clientId) throws DataNotFoundException {
        List<ClientAccount> accounts = clientAccountDao.getAllClientAccountsByAccountType(clientId, AccountTypeCodeEnum.CFCA);
        if (accounts == null || accounts.isEmpty()) {
            throw new DataNotFoundException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_110.getLabelKey(), null, locale),
                    ResponseEnum.RESPONSE_CODE_110
            );
        }

        Map<ClientAccount, Conversion> accountCurrencyConversions = new HashMap<>();
        accounts.forEach(clientAccount -> {
            CurrencyConversionRate conversionRate = currencyConversionRateDao.getCurrencyConversionRateByCurrencyCode(clientAccount.getCurrency().getCurrencyCode());
            BigDecimal convertedBalance;
            if (conversionRate.getConversionIndicator() == '/') {
                convertedBalance = clientAccount.getDisplayBalance().divide(conversionRate.getRate(), RoundingMode.CEILING);
            } else {
                convertedBalance = clientAccount.getDisplayBalance().multiply(conversionRate.getRate());
            }
            convertedBalance.setScale(clientAccount.getCurrency().getDecimalPlaces(), RoundingMode.CEILING);
            Conversion conversion = new Conversion(conversionRate.getRate(), convertedBalance);
            accountCurrencyConversions.put(clientAccount, conversion);
        });

        return accountCurrencyConversions;
    }

    @Override
    @Transactional
    public Map<Denomination, Integer> withdraw(Integer atmId, Integer clientId, String clientAccountNumber, BigDecimal withdrawalAmount, Date date) throws DataNotFoundException, AtmNotFundedException, InvalidWithdrawalAccountException, LimitExceededException {
        // Check that the ATM ID is actually valid in the system
        Atm atm = atmDao.findById(atmId).orElseThrow(() -> new DataNotFoundException(
                messageSource.getMessage(ResponseEnum.RESPONSE_CODE_111.getLabelKey(), null, locale),
                ResponseEnum.RESPONSE_CODE_111
        ));
        // Check that the ATM has allocations
        if (atm.getAllocations().size() < 1) {
            throw new AtmNotFundedException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_112.getLabelKey(), null, locale),
                    ResponseEnum.RESPONSE_CODE_112
            );
        }
        // Check that the account is transactional and the account number given actually belongs to the given client id
        ClientAccount clientAccount = clientAccountDao.getClientAccountByClientAccountNumber(clientId, clientAccountNumber, true)
                .orElseThrow(() -> new InvalidWithdrawalAccountException(
                        messageSource.getMessage(ResponseEnum.RESPONSE_CODE_113.getLabelKey(), null, locale),
                        ResponseEnum.RESPONSE_CODE_113
                ));
        // make sure that the client has enough balance (if a chq account, we can go upto a negative balance of 10000)
        if (isWithdrawalLimitExceeded(clientAccount, withdrawalAmount)) {
            throw new LimitExceededException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_114.getLabelKey(), null, locale),
                    ResponseEnum.RESPONSE_CODE_114
            );
        }

        // Let's filter out and get only the allocations with Note denominations and the allocations that still have notes we can dispense
        List<AtmAllocation> dispensableNoteAllocations = atm.getAllocations().stream().filter((atmAllocation ->
                (DenominationTypeCodeEnum.N.equals(atmAllocation.getDenomination().getDenominationType().getDenominationTypeCode()))
                    && atmAllocation.getCount() > 0)
        ).collect(Collectors.toList());

        if (dispensableNoteAllocations.isEmpty()) {
            throw new AtmNotFundedException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_112.getLabelKey(), null, locale),
                    ResponseEnum.RESPONSE_CODE_112
            );
        }

        // Sort the allocations from the largest to the smallest so that we are using the least amount of notes
        Comparator<AtmAllocation> allocationsComparator = Comparator.comparing(AtmAllocation::getDenomination, Comparator.comparing(Denomination::getValue)).reversed();
        dispensableNoteAllocations.sort(allocationsComparator);

        // check if we can dispense the amount required or determine the nearest amount we can dispense from the atm
        Map<Denomination, Integer> dispensableValue = getDispensableValue(dispensableNoteAllocations, withdrawalAmount);

        // We need to throw an exception that states that you need to enter an amount that the atm can actually dispense
        if (dispensableValue.isEmpty()) {
            throw new LimitExceededException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_115.getLabelKey(),
                            new Object[] {dispensableNoteAllocations.get(dispensableNoteAllocations.size() - 1).getDenomination().getValue().toPlainString()},
                            locale
                    ),
                    ResponseEnum.RESPONSE_CODE_115
            );
        }

        // Get a sum of the amount we are able to dispense
        BigDecimal sumDispensableAmount = dispensableValue.entrySet().stream().map(valueEntry ->
                valueEntry.getKey().getValue().multiply(new BigDecimal(valueEntry.getValue()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        // If the amount we can dispense is less than the amount we requested for then we let the customer know what we can do
        if (sumDispensableAmount.compareTo(withdrawalAmount) < 0) {
            throw new AtmNotFundedException(
                    messageSource.getMessage(ResponseEnum.RESPONSE_CODE_116.getLabelKey(),
                            new Object[] {sumDispensableAmount.toPlainString()},
                            locale
                    ),
                    ResponseEnum.RESPONSE_CODE_116
            );
        }

        // Adjust the balance
        clientAccount.setDisplayBalance(clientAccount.getDisplayBalance().subtract(sumDispensableAmount));
        clientAccountDao.save(clientAccount);

        return dispensableValue;
    }

    private Map<Denomination, Integer> getDispensableValue(List<AtmAllocation> dispensableNoteAllocations, BigDecimal amountToDispense) {
        Map<Denomination, Integer> dispensableValue = new HashMap<>();
        BigDecimal remainingAmountToBeDispensed = amountToDispense;

        for (AtmAllocation noteAllocation : dispensableNoteAllocations) {
            // We first want to check of we can serve for this note value, if not we just ned to move on
            // if the amount is less than the note value we want to move on to the next note value available
            if (remainingAmountToBeDispensed.compareTo(noteAllocation.getDenomination().getValue()) >= 0) {
                int numberOfNotes = remainingAmountToBeDispensed.divide(noteAllocation.getDenomination().getValue()).intValue();
                if (numberOfNotes > noteAllocation.getCount()) {
                    numberOfNotes = noteAllocation.getCount();
                }
                dispensableValue.put(noteAllocation.getDenomination(), numberOfNotes);
                remainingAmountToBeDispensed = remainingAmountToBeDispensed.subtract(noteAllocation.getDenomination().getValue().multiply(new BigDecimal(numberOfNotes)));
            } else {
                continue;
            }

            if (remainingAmountToBeDispensed.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        return dispensableValue;
    }

    private boolean isWithdrawalLimitExceeded(ClientAccount account, BigDecimal amount) {
        BigDecimal newBalance = account.getDisplayBalance().subtract(amount);
        if (AccountTypeCodeEnum.CHQ.equals(account.getAccountType().getAccountTypeCode())) {
            return newBalance.compareTo(new BigDecimal(-10000)) < 0;
        }

        return newBalance.compareTo(BigDecimal.ZERO) < 0;
    }
}