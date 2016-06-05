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

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}

}
