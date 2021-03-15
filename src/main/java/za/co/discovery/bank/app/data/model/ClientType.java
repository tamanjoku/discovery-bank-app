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
 * This class is used to represent available client types on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CLIENT_TYPE")
public class ClientType implements Serializable, Comparable<ClientType> {

    @Id
    @Column(name = "CLIENT_TYPE_CODE", nullable = false, length = 2)
    private String clientTypeCode;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public ClientType() {
        super();
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String accountTypeCode) {
        this.clientTypeCode = accountTypeCode;
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
        ClientType rhs = (ClientType) obj;
        return new EqualsBuilder().append(clientTypeCode, rhs.clientTypeCode).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(13, 33).append(clientTypeCode).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(ClientType o) {
        return new CompareToBuilder().append(this.clientTypeCode, o.clientTypeCode).toComparison();
    }

}
