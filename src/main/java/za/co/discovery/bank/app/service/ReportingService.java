package za.co.discovery.bank.app.service;

import za.co.discovery.bank.app.data.model.reporting.FinancialPositionReport;
import za.co.discovery.bank.app.data.model.reporting.TransactionalAccountReport;

import java.util.List;

/**
 * Interface that lists methods that extract data for reporting purposes
 * @author Torti Ama-Njoku @ Discovery
 */
public interface ReportingService {

    List<TransactionalAccountReport> getTransactionalAccountsReport();

    List<FinancialPositionReport> getFinancialPositionReport();
}