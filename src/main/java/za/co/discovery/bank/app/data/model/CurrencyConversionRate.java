package za.co.discovery.bank.app.data.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is used to represent available currency conversion rates on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CURRENCY_CONVERSION_RATE")
public class CurrencyConversionRate implements Serializable, Comparable<CurrencyConversionRate> {

    @Id
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CURRENCY_CODE", referencedColumnName = "CURRENCY_CODE", nullable = false, insertable = false, updatable = false)
    private Currency currency;

    @Column(name = "CONVERSION_INDICATOR", nullable = false, length = 1, columnDefinition = "VARCHAR")
    private char conversionIndicator;

    @Column(name = "RATE", nullable = false)
    private BigDecimal rate;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public CurrencyConversionRate() {
        super();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public char getConversionIndicator() {
        return conversionIndicator;
    }

    public void setConversionIndicator(char conversionIndicator) {
        this.conversionIndicator = conversionIndicator;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        CurrencyConversionRate rhs = (CurrencyConversionRate) obj;
        return new EqualsBuilder().append(currency, rhs.currency).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(19, 39).append(currency).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(CurrencyConversionRate o) {
        return new CompareToBuilder().append(this.currency, o.currency).toComparison();
    }

}
