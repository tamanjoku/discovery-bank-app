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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * This class is used to represent available clients on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable, Comparable<Client> {

    @Id
    @Column(name = "CLIENT_ID")
    private Integer clientId;

    @Column(name = "TITLE", length = 10)
    private String title;

    @Column(name = "NAME", nullable = false, length = 255)
    private String firstName;

    @Column(name = "SURNAME", length = 100)
    private String lastName;

    @Column(name = "DOB", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CLIENT_SUB_TYPE_CODE", referencedColumnName = "CLIENT_SUB_TYPE_CODE", nullable = false, updatable = false)
    private ClientSubType clientSubType;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Client() {
        super();
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ClientSubType getClientSubType() {
        return clientSubType;
    }

    public void setClientSubType(ClientSubType clientSubType) {
        this.clientSubType = clientSubType;
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
        Client rhs = (Client) obj;
        return new EqualsBuilder().append(clientId, rhs.clientId).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(29, 49).append(clientId).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(Client o) {
        return new CompareToBuilder().append(this.clientId, o.clientId).toComparison();
    }

}
