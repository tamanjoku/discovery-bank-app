package za.co.discovery.bank.app.data.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import za.co.discovery.bank.app.enums.DenominationTypeCodeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This class is used to represent available denomination types on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "DENOMINATION_TYPE")
public class DenominationType implements Serializable, Comparable<DenominationType> {

    @Id
    @Column(name = "DENOMINATION_TYPE_CODE", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private DenominationTypeCodeEnum denominationTypeCode;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public DenominationType() {
        super();
    }

    public DenominationTypeCodeEnum getDenominationTypeCode() {
        return denominationTypeCode;
    }

    public void setDenominationTypeCode(DenominationTypeCodeEnum denominationTypeCode) {
        this.denominationTypeCode = denominationTypeCode;
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
        DenominationType rhs = (DenominationType) obj;
        return new EqualsBuilder().append(denominationTypeCode, rhs.denominationTypeCode).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(21, 41).append(denominationTypeCode).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(DenominationType o) {
        return new CompareToBuilder().append(this.denominationTypeCode, o.denominationTypeCode).toComparison();
    }

}
