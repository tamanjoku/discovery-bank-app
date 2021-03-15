package za.co.discovery.bank.app.service;

import za.co.discovery.bank.app.data.model.Atm;
import za.co.discovery.bank.app.data.model.ClientAccount;
import za.co.discovery.bank.app.data.model.Denomination;
import za.co.discovery.bank.app.data.model.dto.Conversion;
import za.co.discovery.bank.app.exception.AtmNotFundedException;
import za.co.discovery.bank.app.exception.DataNotFoundException;
import za.co.discovery.bank.app.exception.InvalidWithdrawalAccountException;
import za.co.discovery.bank.app.exception.LimitExceededException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface service class for executing desired {@link ClientAccount} object operations
 * @author Torti Ama-Njoku @ Discovery
 */
public interface ClientAccountService {
    
    List<ClientAccount> getTransactionalClientAccounts(Integer clientId) throws DataNotFoundException;

    Map<ClientAccount, Conversion> getCurrencyClientAccounts(Integer clientId) throws DataNotFoundException;

    Map<Denomination, Integer> withdraw(Integer atmId, Integer clientId, String clientAccountNumber,
                                        BigDecimal withdrawalAmount, Date date)
            throws DataNotFoundException, AtmNotFundedException, InvalidWithdrawalAccountException, LimitExceededException;
}