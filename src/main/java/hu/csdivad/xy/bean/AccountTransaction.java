package hu.csdivad.xy.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class AccountTransaction implements Serializable {
	private Account senderAccount;
	private Account recipientAccount;
	private Calendar transactionTime;
}
