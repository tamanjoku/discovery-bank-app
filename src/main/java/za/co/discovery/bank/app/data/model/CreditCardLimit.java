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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is used to represent available credit card limits on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CREDIT_CARD_LIMIT")
public class CreditCardLimit implements Serializable, Comparable<CreditCardLimit> {

    @Id
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CLIENT_ACCOUNT_NUMBER", referencedColumnName = "CLIENT_ACCOUNT_NUMBER", nullable = false, updatable = false)
    private ClientAccount clientAccount;

    @Column(name = "ACCOUNT_LIMIT", nullable = false)
    private BigDecimal accountLimit;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public CreditCardLimit() {
        super();
    }

    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    public BigDecimal getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
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
        CreditCardLimit rhs = (CreditCardLimit) obj;
        return new EqualsBuilder().append(clientAccount.getClientAccountNumber(), rhs.clientAccount.getClientAccountNumber()).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(33, 53).append(clientAccount.getClientAccountNumber()).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(CreditCardLimit o) {
        return new CompareToBuilder().append(this.clientAccount.getClientAccountNumber(), o.clientAccount.getClientAccountNumber()).toComparison();
    }

}
