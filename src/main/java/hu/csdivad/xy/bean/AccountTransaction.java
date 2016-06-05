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
	
	@Column(name = "transaction_time", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar transactionTime;

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
	
	
	
}
