package za.co.discovery.bank.app.data.model.reporting;

import java.math.BigDecimal;

/**
 * This class holds data of a client's account details and the display balance of the transactional account with the
 * highest balance.
 *
 * @author Torti Ama-Njoku @ Discovery
 */
public class TransactionalAccountReport {

    private Integer clientId;
    private String surname;
    private String accountNumber;
    private String accountDescription;
    private BigDecimal displayBalance;

    public TransactionalAccountReport(Integer clientId, String surname, String accountNumber, String accountDescription, BigDecimal displayBalance) {
        this.clientId = clientId;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.accountDescription = accountDescription;
        this.displayBalance = displayBalance;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public BigDecimal getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(BigDecimal displayBalance) {
        this.displayBalance = displayBalance;
    }
}
