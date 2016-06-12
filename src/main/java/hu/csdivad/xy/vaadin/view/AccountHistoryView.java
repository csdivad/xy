package hu.csdivad.xy.vaadin.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.AccountTransaction;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;

@SpringView(name = AccountHistoryView.VIEW_NAME)
public class AccountHistoryView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "account-history";
	
	@Autowired
	private Account account;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public AccountHistoryView() {
		setSizeFull();
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addComponent(createTranscationHistory());
	}
	
	private Component createTranscationHistory() {		
		Table table = new Table("Számlatörténet");
		table.addContainerProperty("Küldő számlaszáma", Integer.class, null);
		table.addContainerProperty("Fogadó számlaszáma", Integer.class, null);
		table.addContainerProperty("Összeg", Integer.class, null);
		table.addContainerProperty("Utalás ideje", Label.class, null);

		List<AccountTransaction> transactions = new ArrayList<>();

		transactions.addAll(account.getIncomingTransactions());
		transactions.addAll(account.getOutgoingTransactions());
		transactions.sort((t1, t2) -> t1.getTransactionTime().compareTo(t2.getTransactionTime()));

		for (AccountTransaction transaction : transactions) {
			int senderID = transaction.getSenderAccount().getAccountId();
			int recipientID = transaction.getRecipientAccount().getAccountId();
			int amount = transaction.getAmount();
			Calendar transactionTime = transaction.getTransactionTime();
			Label dateTimeLabel = new Label(formatter.format(transactionTime.getTime()));
			table.addItem(new Object[] { senderID, recipientID, amount, dateTimeLabel }, null);
		}

		return table;
	}

}
