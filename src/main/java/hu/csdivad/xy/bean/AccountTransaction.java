package hu.csdivad.xy.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions")
public class AccountTransaction implements Serializable {
	private static final long serialVersionUID = -6296676707444965829L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id", unique = true, nullable = false)
	private int transactionId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_account_id", nullable = false)
	private Account senderAccount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient_account_id", nullable = false)
	private Account recipientAccount;

	@Column(name = "transaction_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar transactionTime;

	@Column(name = "amount", nullable = false)
	private int amount;

	public AccountTransaction() {
	}

	public AccountTransaction(Account senderAccount, Account recipientAccount, Calendar transactionTime, int amount) {
		this.senderAccount = senderAccount;
		this.recipientAccount = recipientAccount;
		this.transactionTime = transactionTime;
		this.amount = amount;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getRecipientAccount() {
		return recipientAccount;
	}

	public void setRecipientAccount(Account recipientAccount) {
		this.recipientAccount = recipientAccount;
	}

	public Calendar getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Calendar transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipientAccount == null) ? 0 : recipientAccount.hashCode());
		result = prime * result + ((senderAccount == null) ? 0 : senderAccount.hashCode());
		result = prime * result + transactionId;
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
		AccountTransaction other = (AccountTransaction) obj;
		if (recipientAccount == null) {
			if (other.recipientAccount != null)
				return false;
		} else if (!recipientAccount.equals(other.recipientAccount))
			return false;
		if (senderAccount == null) {
			if (other.senderAccount != null)
				return false;
		} else if (!senderAccount.equals(other.senderAccount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}

}
