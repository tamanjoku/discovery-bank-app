package za.co.discovery.bank.app.rest.response.entities;

import java.math.BigDecimal;

public class TransactionalAccountResponse {
    private String accountNumber;
    private String accountType;
    private BigDecimal accountBalance;

    public TransactionalAccountResponse(String accountNumber, String accountType, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
