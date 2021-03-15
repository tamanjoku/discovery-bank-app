package za.co.discovery.bank.app.data.model.reporting;

import java.math.BigDecimal;

/**
 * This class holds data of a client's financial position, detailing their liabilities and assets and net balance sheet.
 *
 * @author Torti Ama-Njoku @ Discovery
 */
public class FinancialPositionReport {

    private String client;
    private BigDecimal loanBalance;
    private BigDecimal transactionalBalance;
    private BigDecimal netPosition;

    public FinancialPositionReport(String client, BigDecimal loanBalance, BigDecimal transactionalBalance, BigDecimal netPosition) {
        this.client = client;
        this.loanBalance = loanBalance;
        this.transactionalBalance = transactionalBalance;
        this.netPosition = netPosition;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public BigDecimal getTransactionalBalance() {
        return transactionalBalance;
    }

    public void setTransactionalBalance(BigDecimal transactionalBalance) {
        this.transactionalBalance = transactionalBalance;
    }

    public BigDecimal getNetPosition() {
        return netPosition;
    }

    public void setNetPosition(BigDecimal netPosition) {
        this.netPosition = netPosition;
    }
}
