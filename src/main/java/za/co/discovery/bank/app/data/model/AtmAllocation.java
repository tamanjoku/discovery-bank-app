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

/**
 * This class is used to represent available ATM Allocations on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "ATM_ALLOCATION")
public class AtmAllocation implements Serializable, Comparable<AtmAllocation> {

    @Id
    @Column(name = "ATM_ALLOCATION_ID", nullable = false)
    private Integer atmAllocationId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ATM_ID", referencedColumnName = "ATM_ID", nullable = false, updatable = false)
    private Atm atm;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "DENOMINATION_ID", referencedColumnName = "DENOMINATION_ID", nullable = false, updatable = false)
    private Denomination denomination;

    @Column(name = "COUNT", nullable = false)
    private Integer count;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public AtmAllocation() {
        super();
    }

    public Integer getAtmAllocationId() {
        return atmAllocationId;
    }

    public void setAtmAllocationId(Integer atmAllocationId) {
        this.atmAllocationId = atmAllocationId;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        AtmAllocation rhs = (AtmAllocation) obj;
        return new EqualsBuilder()
                .append(atmAllocationId, rhs.atmAllocationId)
                .append(atm, rhs.atm)
                .append(denomination, rhs.denomination)
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(27, 47)
                .append(atmAllocationId)
                .append(atm.getAtmId())
                .append(denomination.getDenominationId())
                .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(AtmAllocation o) {
        return new CompareToBuilder()
                .append(this.atmAllocationId, o.atmAllocationId)
                .append(this.atm, o.atm)
                .append(this.denomination, o.denomination)
                .toComparison();
    }

}
