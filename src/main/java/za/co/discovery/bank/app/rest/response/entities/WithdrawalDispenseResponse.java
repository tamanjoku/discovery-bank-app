package za.co.discovery.bank.app.rest.response.entities;

import java.math.BigDecimal;

public class WithdrawalDispenseResponse {

    private BigDecimal denomination;
    private Integer numberOfNotes;

    public WithdrawalDispenseResponse(BigDecimal denomination, Integer numberOfNotes) {
        this.denomination = denomination;
        this.numberOfNotes = numberOfNotes;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public Integer getNumberOfNotes() {
        return numberOfNotes;
    }

    public void setNumberOfNotes(Integer numberOfNotes) {
        this.numberOfNotes = numberOfNotes;
    }
}
