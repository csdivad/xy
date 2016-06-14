package hu.csdivad.xy.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 3312306344285538527L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", unique = true, nullable = false)
	private Integer accountId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Column(name = "balance", nullable = false)
	private int balance;

	// TODO FetchType.LAZY
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "senderAccount")
	private Set<AccountTransaction> outgoingTransactions = new HashSet<AccountTransaction>(0);

	// TODO FetchType.LAZY
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recipientAccount")
	private Set<AccountTransaction> incomingTransactions = new HashSet<AccountTransaction>(0);

	public Account() {
	}

	public Account(Integer accountId) {
		super();
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Set<AccountTransaction> getOutgoingTransactions() {
		return outgoingTransactions;
	}

	public void setOutgoingTransactions(Set<AccountTransaction> outgoingTransactions) {
		this.outgoingTransactions = outgoingTransactions;
	}

	public Set<AccountTransaction> getIncomingTransactions() {
		return incomingTransactions;
	}

	public void setIncomingTransactions(Set<AccountTransaction> incomingTransactions) {
		this.incomingTransactions = incomingTransactions;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		return true;
	}

}
