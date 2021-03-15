package za.co.discovery.bank.app.rest.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * REST webservice request entity for a withdrawal
 * @author Torti Ama-Njoku @ Discovery
 */
public class WithdrawalDataHolderRequest {

    @NotNull
    private Integer clientId;

    @NotNull
    private String clientAccountNumber;

    @NotNull
    private BigDecimal withdrawalAmount;

    @NotNull
    private Date withdrawalDate;
    
    public WithdrawalDataHolderRequest() {
        super();
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}
