package za.co.discovery.bank.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.bank.app.data.model.ClientAccount;
import za.co.discovery.bank.app.data.model.dto.Conversion;
import za.co.discovery.bank.app.enums.ResponseEnum;
import za.co.discovery.bank.app.exception.DataNotFoundException;
import za.co.discovery.bank.app.rest.response.CurrencyAccountsListDataHolderResponse;
import za.co.discovery.bank.app.rest.response.TransactionalAccountsListDataHolderResponse;
import za.co.discovery.bank.app.rest.response.entities.CurrencyAccountResponse;
import za.co.discovery.bank.app.rest.response.entities.TransactionalAccountResponse;
import za.co.discovery.bank.app.service.ClientAccountService;

import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client-accounts")
public class ClientAccountsApiController {

    private final ClientAccountService clientAccountService;
    private final MessageSource messageSource;

    private static final Locale locale = Locale.UK;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAccountsApiController.class);

    public ClientAccountsApiController(final ClientAccountService clientAccountService, final MessageSource messageSource) {
        this.clientAccountService = clientAccountService;
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/{clientId}/transactional", method = RequestMethod.GET)
    public ResponseEntity<TransactionalAccountsListDataHolderResponse> getTransactionalClientAccounts(@PathVariable("clientId") Integer clientId) throws DataNotFoundException {

        LOGGER.debug("Calling getTransactionalClientAccounts for client {}", clientId);
        
        List<ClientAccount> accounts = clientAccountService.getTransactionalClientAccounts(clientId);
        List<TransactionalAccountResponse> responseData = accounts.stream()
                .map(account ->
                    new TransactionalAccountResponse(
                            account.getClientAccountNumber(),
                            account.getAccountType().getDescription(),
                            account.getDisplayBalance().setScale(account.getCurrency().getDecimalPlaces())
                    )
                )
                .sorted(Comparator.comparing(TransactionalAccountResponse::getAccountBalance).reversed())
                .collect(Collectors.toList());

        TransactionalAccountsListDataHolderResponse response = new TransactionalAccountsListDataHolderResponse(
                ResponseEnum.SUCCESSFUL.getCode(),
                messageSource.getMessage(ResponseEnum.SUCCESSFUL.getLabelKey(),
                        new Object[]{messageSource.getMessage("client.accounts.transactional.getAll.successful", null, locale)},
                        locale),
                ResponseEnum.SUCCESSFUL.getHttpCode().value(),
                new Date().getTime(),
                responseData
        );

        return new ResponseEntity<>(response, ResponseEnum.SUCCESSFUL.getHttpCode());
    }

    @RequestMapping(value = "/{clientId}/currency", method = RequestMethod.GET)
    public ResponseEntity<CurrencyAccountsListDataHolderResponse> getCurrencyClientAccounts(@PathVariable("clientId") Integer clientId) throws DataNotFoundException {

        LOGGER.debug("Calling getCurrencyClientAccounts for client {}", clientId);

        Map<ClientAccount, Conversion> accountsWithConversion = clientAccountService.getCurrencyClientAccounts(clientId);
        List<CurrencyAccountResponse> responseData = accountsWithConversion.entrySet().stream()
                .map(accountWithConversion ->
                        new CurrencyAccountResponse(
                                accountWithConversion.getKey().getClientAccountNumber(),
                                accountWithConversion.getKey().getCurrency().getCurrencyCode(),
                                accountWithConversion.getKey().getDisplayBalance().setScale(accountWithConversion.getKey().getCurrency().getDecimalPlaces(), RoundingMode.CEILING),
                                accountWithConversion.getValue().getRate(),
                                accountWithConversion.getValue().getAmount().setScale(2, RoundingMode.CEILING)
                        )
                )
                .sorted(Comparator.comparing(CurrencyAccountResponse::getLocalAmount).reversed())
                .collect(Collectors.toList());

        CurrencyAccountsListDataHolderResponse response = new CurrencyAccountsListDataHolderResponse(
                ResponseEnum.SUCCESSFUL.getCode(),
                messageSource.getMessage(ResponseEnum.SUCCESSFUL.getLabelKey(),
                        new Object[]{messageSource.getMessage("client.accounts.currency.getAll.successful", null, locale)},
                        locale),
                ResponseEnum.SUCCESSFUL.getHttpCode().value(),
                new Date().getTime(),
                responseData
        );

        return new ResponseEntity<>(response, ResponseEnum.SUCCESSFUL.getHttpCode());
    }
}
