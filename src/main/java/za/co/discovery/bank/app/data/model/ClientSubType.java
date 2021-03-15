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
 * This class is used to represent available client sub types on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CLIENT_SUB_TYPE")
public class ClientSubType implements Serializable, Comparable<ClientSubType> {

    @Id
    @Column(name = "CLIENT_SUB_TYPE_CODE", nullable = false, length = 4)
    private String clientSubTypeCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CLIENT_TYPE_CODE", referencedColumnName = "CLIENT_TYPE_CODE", nullable = false, updatable = false)
    private ClientType clientType;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public ClientSubType() {
        super();
    }

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String accountTypeCode) {
        this.clientSubTypeCode = accountTypeCode;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
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
        ClientSubType rhs = (ClientSubType) obj;
        return new EqualsBuilder().append(clientSubTypeCode, rhs.clientSubTypeCode).append(clientType, rhs.clientType).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(15, 35).append(clientSubTypeCode).append(clientType.getClientTypeCode()).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(ClientSubType o) {
        return new CompareToBuilder().append(this.clientSubTypeCode, o.clientSubTypeCode).append(clientType, o.clientType).toComparison();
    }

}
