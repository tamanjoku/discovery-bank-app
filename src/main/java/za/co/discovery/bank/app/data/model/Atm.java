package za.co.discovery.bank.app.data.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent available ATMs on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "ATM")
public class Atm implements Serializable, Comparable<Atm> {

    @Id
    @Column(name = "ATM_ID", nullable = false)
    private Integer atmId;

    @Column(name = "NAME", nullable = false, length = 10, unique = true)
    private String name;

    @Column(name = "LOCATION", nullable = false, length = 255)
    private String location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "atm")
    @Fetch(FetchMode.SELECT)
    private List<AtmAllocation> allocations = new ArrayList<>(0);

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Atm() {
        super();
    }

    public Integer getAtmId() {
        return atmId;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<AtmAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<AtmAllocation> allocations) {
        this.allocations = allocations;
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
        Atm rhs = (Atm) obj;
        return new EqualsBuilder().append(atmId, rhs.atmId).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(25, 45).append(atmId).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(Atm o) {
        return new CompareToBuilder().append(this.atmId, o.atmId).toComparison();
    }

}
