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
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.dao.AccountDao;
import hu.csdivad.xy.dao.AccountTransactionDao;

@SpringView(name = InitiateTransactionView.VIEW_NAME)
public class InitiateTransactionView extends VerticalLayout implements View, ClickListener {
	private static final long serialVersionUID = -6939927305165688758L;
	public static final String VIEW_NAME = "initiate-transaction";

	@Autowired
	private AccountTransactionDao accountTransactionDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private Account loggedInAccount;
	private TextField recipientAccountIdField = new TextField("Kedvezményezett számlaszáma");
	private TextField amountField = new TextField("Átutatlás összege");
	private Button beginTransferButton = new Button("Indítás");
	private FormLayout formLayout = new FormLayout();

	public InitiateTransactionView() {
		recipientAccountIdField.addValidator(new NullValidator("A kedvezményezett számlaszáma hiányzik", false));
		amountField.addValidator(new NullValidator("Az átutalás összege hiányzik", false));
		setSizeFull();
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(formLayout);
	}

	@PostConstruct
	public void init() {
		beginTransferButton.addClickListener(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		formLayout.removeAllComponents();
		formLayout.addComponents(recipientAccountIdField, amountField, beginTransferButton);
		formLayout.setWidthUndefined();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		int amount = Integer.parseInt(amountField.getValue());
		Account recipientAccount = accountDao.findAccountById(Integer.parseInt(recipientAccountIdField.getValue()));
		if (loggedInAccount.equals(recipientAccount)) {
			Notification.show("A célszámla megegyezik az ön jelenlegi számlájával!");
			return;
		}

		if (doTransaction(loggedInAccount, recipientAccount, amount)) {
			Notification.show("Sikeres tranzakció");
			getUI().getNavigator().navigateTo(AccountDetailsView.VIEW_NAME);
		} else {
			Notification.show("Sikertelen tranzakció", Type.ERROR_MESSAGE);
		}
	}

	public boolean doTransaction(Account from, Account to, int amount) {
		int newBalance = from.getBalance() - amount;
		if (newBalance < 0) {
			Notification.show("Nincs elég pénz a számlán", Type.TRAY_NOTIFICATION);
			return false;
		}

		return accountTransactionDao.moneyTransfer(from, to, amount, Calendar.getInstance());
	}
}
