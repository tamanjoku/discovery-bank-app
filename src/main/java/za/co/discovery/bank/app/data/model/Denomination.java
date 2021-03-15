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
 * This class is used to represent available denominations on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "DENOMINATION")
public class Denomination implements Serializable, Comparable<Denomination> {

    @Id
    @Column(name = "DENOMINATION_ID", nullable = false)
    private Integer denominationId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DENOMINATION_TYPE_CODE", referencedColumnName = "DENOMINATION_TYPE_CODE", nullable = false)
    private DenominationType denominationType;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Denomination() {
        super();
    }

    public Integer getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Integer denominationId) {
        this.denominationId = denominationId;
    }

    public DenominationType getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(DenominationType denominationType) {
        this.denominationType = denominationType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
        Denomination rhs = (Denomination) obj;
        return new EqualsBuilder().append(denominationId, rhs.denominationId).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(23, 43).append(denominationId).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(Denomination o) {
        return new CompareToBuilder().append(this.denominationId, o.denominationId).toComparison();
    }

}
