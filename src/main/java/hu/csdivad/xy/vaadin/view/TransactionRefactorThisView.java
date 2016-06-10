package hu.csdivad.xy.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

public class TransactionRefactorThisView extends CustomLayout implements View{
	public static final String VIEW_NAME = "transaction-refactor-view";

	@Autowired
	private UserDao userDao;
	private User user;
	private Account selectedAccount;

	@Override
	public void enter(ViewChangeEvent event) {
		addComponent(createAccountSelector());
		addComponent(createTranscationHistory());
	}

	private Component createAccountSelector() {
		if (user == null || user.getAccounts() == null) {
			return new Label("User Error");
		} // TODO Exception?

		NativeSelect accountSelector = new NativeSelect();
		accountSelector.addItems(user.getAccounts());
		if (accountSelector.size() > 0) {
			accountSelector.setValue(0);
		}
		return accountSelector;
	}

	@PostConstruct
	private void init() {
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private Component createTranscationHistory() {
		if (user == null || user.getUserDetails() == null) {
			return new Label("User Error");
		}
		VerticalLayout transactionsLayout = new VerticalLayout();
		// for(AccountTransaction transaction :)

		return transactionsLayout;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
