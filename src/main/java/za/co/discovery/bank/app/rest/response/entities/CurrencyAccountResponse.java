package za.co.discovery.bank.app.rest.response.entities;

import java.math.BigDecimal;

public class CurrencyAccountResponse {
    private String accountNumber;
    private String currency;
    private BigDecimal currencyBalance;
    private BigDecimal conversionRate;
    private BigDecimal localAmount;

    public CurrencyAccountResponse(String accountNumber, String currency, BigDecimal currencyBalance, BigDecimal conversionRate, BigDecimal localAmount) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.currencyBalance = currencyBalance;
        this.conversionRate = conversionRate;
        this.localAmount = localAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(BigDecimal currencyBalance) {
        this.currencyBalance = currencyBalance;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }
}
