package hu.csdivad.xy.vaadin.view;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

@SpringView(name = AccountDetailsView.VIEW_NAME)
public class AccountDetailsView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "account-details";

	@Autowired
	private UserDao userDao;
	@Autowired
	private Account loggedInAccount;
	@Autowired
	private User user;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public AccountDetailsView() {
		setSizeFull();
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addComponent(createUserInfoForm());
	}

	private Component createUserInfoForm() {
		if (user == null || user.getUserDetails() == null) {
			return new Label("User Error");
		}
		VerticalLayout userInfoLayout = new VerticalLayout();
		userInfoLayout.setSizeFull();
		userInfoLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Label usernameLbl = new Label("Ügyfélazonosító: " + user.getUsername());
		Label nameLbl = new Label(
				"Név: " + user.getUserDetails().getGivenName() + " " + user.getUserDetails().getFamilyName());
		Label balanceLbl = new Label("Egyenleg: " + loggedInAccount.getBalance());
		usernameLbl.setSizeUndefined();
		nameLbl.setSizeUndefined();
		balanceLbl.setSizeUndefined();
		userInfoLayout.addComponents(usernameLbl, nameLbl, balanceLbl);

		if (user.getLastLogin() != null) {
			Label lastLoginLbl = new Label("Last login: " + formatter.format(user.getLastLogin().getTime()));
			lastLoginLbl.setSizeUndefined();
			userInfoLayout.addComponent(lastLoginLbl);
		}

		return userInfoLayout;
	}

}
