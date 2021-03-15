package za.co.discovery.bank.app.service.impl;

import org.springframework.stereotype.Service;
import za.co.discovery.bank.app.dao.ClientAccountDao;
import za.co.discovery.bank.app.data.model.ClientAccount;
import za.co.discovery.bank.app.data.model.reporting.FinancialPositionReport;
import za.co.discovery.bank.app.data.model.reporting.TransactionalAccountReport;
import za.co.discovery.bank.app.service.ReportingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default Implementation of ClientAccountService interface.
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
@Service("reportingService")
public class ReportingServiceImpl implements ReportingService {

    private final ClientAccountDao clientAccountDao;

    /**
     * Used by spring to instantiate a bean of this class autowiring by argument
     * resolvers the parameters in this constructor.
     */
    public ReportingServiceImpl(
            final ClientAccountDao clientAccountDao
    ) {
        this.clientAccountDao = clientAccountDao;
    }


    @Override
    public List<TransactionalAccountReport> getTransactionalAccountsReport() {
        return clientAccountDao.getTransactionalAccountsReport().stream()
                .map(clientAccount -> new TransactionalAccountReport(
                        clientAccount.getClient().getClientId(),
                        clientAccount.getClient().getLastName(),
                        clientAccount.getClientAccountNumber(),
                        clientAccount.getAccountType().getDescription(),
                        clientAccount.getDisplayBalance()
                )).collect(Collectors.toList());
    }

    @Override
    public List<FinancialPositionReport> getFinancialPositionReport() {
        return clientAccountDao.getFinancialPositionReport().stream()
                .map(row -> new FinancialPositionReport(
                            row[0].toString(), // Client full name
                            new BigDecimal(row[1].toString()), // loan balance
                            new BigDecimal(row[2].toString()), // transactional balance
                            new BigDecimal(row[3].toString()) // net position
                        )
                )
                .collect(Collectors.toList());
    }
}