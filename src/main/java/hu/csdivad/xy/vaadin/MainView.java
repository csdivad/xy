package hu.csdivad.xy.vaadin;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

@SpringView(name = "main")
@UIScope
public class MainView extends VerticalLayout implements View {

	@Autowired
	private UserDao userDao;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	private Button logout = new Button("Logout");
	private User user;

	public MainView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@PostConstruct
	private void init() {
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		logout.addClickListener((event) -> logout());
		addComponent(createUserInfoForm());
		addComponent(logout);
	}

	// @Transactional
	private Component createUserInfoForm() {
		if (user == null) {
			return new Label("User Error");
		}
		VerticalLayout userInfo = new VerticalLayout();

		userInfo.addComponent(new Label("Username: " + user.getUsername()));
		userInfo.addComponent(new Label(
				"Name: " + user.getUserDetails().getGivenName() + " " + user.getUserDetails().getFamilyName()));
		if (user.getLastLogin() != null) {
			userInfo.addComponent(new Label("Last login: " + formatter.format(user.getLastLogin().getTime())));
		}

		// for(Account account : user.getAccounts()) {
		// userInfo.addComponent(new Label(account.toString()));
		// }

		return userInfo;
	}

	private void logout() {
		SecurityContextHolder.clearContext();

		for (UI ui : UI.getCurrent().getSession().getUIs())
			ui.access(() -> {
				ui.getPage().open("main", null); // .setLocation("/logout.html");
			});
		UI.getCurrent().getSession().close();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
