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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is used to represent available client accounts on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "CLIENT_ACCOUNT")
public class ClientAccount implements Serializable, Comparable<ClientAccount> {

    @Id
    @Column(name = "CLIENT_ACCOUNT_NUMBER", nullable = false, length = 10)
    private String clientAccountNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID", nullable = false, updatable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ACCOUNT_TYPE_CODE", referencedColumnName = "ACCOUNT_TYPE_CODE", nullable = false, updatable = false)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CURRENCY_CODE", referencedColumnName = "CURRENCY_CODE", nullable = false, updatable = false)
    private Currency currency;

    @Column(name = "DISPLAY_BALANCE")
    private BigDecimal displayBalance;

    @OneToOne(mappedBy = "clientAccount", fetch = FetchType.EAGER)
    private CreditCardLimit creditCardLimit;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public ClientAccount() {
        super();
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(BigDecimal displayBalance) {
        this.displayBalance = displayBalance;
    }

    public CreditCardLimit getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(CreditCardLimit creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
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
        ClientAccount rhs = (ClientAccount) obj;
        return new EqualsBuilder().append(clientAccountNumber, rhs.clientAccountNumber).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(31, 51).append(clientAccountNumber).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(ClientAccount o) {
        return new CompareToBuilder().append(this.clientAccountNumber, o.clientAccountNumber).toComparison();
    }

}
