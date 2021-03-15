package za.co.discovery.bank.app.data.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import za.co.discovery.bank.app.enums.AccountTypeCodeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This class is used to represent available account types on the database
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType implements Serializable, Comparable<AccountType> {

    @Id
    @Column(name = "ACCOUNT_TYPE_CODE", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AccountTypeCodeEnum accountTypeCode;

    @Column(name = "DESCRIPTION", nullable = false, length = 50)
    private String description;

    @Column(name = "TRANSACTIONAL", columnDefinition = "BIT")
    private Boolean transactional;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public AccountType() {
        super();
    }

    public AccountTypeCodeEnum getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(AccountTypeCodeEnum accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTransactional() {
        return transactional;
    }

    public void setTransactional(Boolean transactional) {
        this.transactional = transactional;
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
        AccountType rhs = (AccountType) obj;
        return new EqualsBuilder().append(accountTypeCode, rhs.accountTypeCode).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(11, 31).append(accountTypeCode).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).build();
    }

    @Override
    public int compareTo(AccountType o) {
        return new CompareToBuilder().append(this.accountTypeCode, o.accountTypeCode).toComparison();
    }

}
