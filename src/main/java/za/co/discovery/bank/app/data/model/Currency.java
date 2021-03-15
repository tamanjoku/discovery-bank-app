package za.co.discovery.bank.app.data.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This class is used to represent available currencies on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable, Comparable<Currency> {

    @Id
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "DECIMAL_PLACES", nullable = false)
    private Integer decimalPlaces;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Currency() {
        super();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String accountTypeCode) {
        this.currencyCode = accountTypeCode;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Currency rhs = (Currency) obj;
        return new EqualsBuilder().append(currencyCode, rhs.currencyCode).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(17, 37).append(currencyCode).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(Currency o) {
        return new CompareToBuilder().append(this.currencyCode, o.currencyCode).toComparison();
    }

}
