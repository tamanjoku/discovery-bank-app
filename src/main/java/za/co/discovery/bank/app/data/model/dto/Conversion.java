package za.co.discovery.bank.app.data.model.dto;

import java.math.BigDecimal;

/**
 * This class holds data of a calculated currency conversion rate and the converted local amount
 *
 * @author Torti Ama-Njoku @ Discovery
 */
public class Conversion {

    private BigDecimal rate;
    private BigDecimal amount;

    public Conversion(BigDecimal rate, BigDecimal amount) {
        this.rate = rate;
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
