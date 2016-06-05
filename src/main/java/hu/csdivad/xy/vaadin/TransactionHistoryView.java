package hu.csdivad.xy.vaadin;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

//TODO VerticalLayout vs CustomComponent+setCompositonRoot, if not new class vs method?
@SpringView(name = TransactionHistoryView.VIEW_NAME)
public class TransactionHistoryView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "transaction-history-view";
	
	@Autowired
	private UserDao userDao;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	private User user;

	public TransactionHistoryView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@PostConstruct
	private void init() {
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addComponent(createTranscationHistory());
	}

	private Component createTranscationHistory() {
		if (user == null || user.getUserDetails() == null) {
			return new Label("User Error");
		}
		VerticalLayout userInfo = new VerticalLayout();

		userInfo.addComponent(new Label("Username: " + user.getUsername()));
		userInfo.addComponent(new Label(
				"Name: " + user.getUserDetails().getGivenName() + " " + user.getUserDetails().getFamilyName()));
		if (user.getLastLogin() != null) {
			userInfo.addComponent(new Label("Last login: " + formatter.format(user.getLastLogin().getTime())));
		}
		
		
		Hibernate.initialize(user);
		for (Account account : user.getAccounts()) {
			userInfo.addComponent(new Label(account.toString()));
		}

		return userInfo;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
