package hu.csdivad.xy.vaadin.view;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.AccountTransaction;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.AccountDao;
import hu.csdivad.xy.dao.AccountTransactionDao;
import hu.csdivad.xy.dao.UserDao;

@SpringView(name = InitiateTransactionView.VIEW_NAME)
public class InitiateTransactionView extends VerticalLayout implements View, ClickListener {
	public static final String VIEW_NAME = "initiate-transaction";

	@Autowired
	private AccountTransactionDao accountTransactionDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private Account loggedInAccount;
	@Autowired
	private User user;
	private TextField recipientAccountIdField = new TextField("Kedvezményezett számlaszáma");
	private TextField amountField = new TextField("Átutatlás összege");
	private Button beginTransferButton = new Button("Indítás");

	public InitiateTransactionView() {
		recipientAccountIdField.addValidator(new NullValidator("A kedvezményezett számlaszáma hiányzik", false));
		amountField.addValidator(new NullValidator("Az átutalás összege hiányzik", false));
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	}

	@PostConstruct
	public void init() {
		beginTransferButton.addClickListener(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addComponents(recipientAccountIdField, amountField, beginTransferButton);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			int amount = Integer.parseInt(amountField.getValue());
			int newBalance = loggedInAccount.getBalance() - amount;
			if(newBalance<0) {
				Notification.show("Nincs elég pénz a számláján");
				return;
			}
			Account recipientAccount = accountDao.findAccountById(Integer.parseInt(recipientAccountIdField.getValue()));
			AccountTransaction accountTransaction = new AccountTransaction(loggedInAccount, recipientAccount, Calendar.getInstance(), amount);
			
			loggedInAccount.setBalance(newBalance);
			recipientAccount.setBalance(recipientAccount.getBalance() + amount);
			accountDao.updateAccount(loggedInAccount);
			accountDao.updateAccount(recipientAccount);
			accountTransactionDao.saveTransaction(accountTransaction);
			Notification.show("Sikeres tranzakció");
			getUI().getNavigator().navigateTo(AccountDetailsView.VIEW_NAME);
		} catch (NumberFormatException ex) {
			
		} catch (Exception ex) {
			
		}
	}

}
