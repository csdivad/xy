package hu.csdivad.xy.vaadin.ui;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.AccountDao;
import hu.csdivad.xy.dao.UserDao;
import hu.csdivad.xy.spring.security.XyWebAuthenticationDetails;
import hu.csdivad.xy.vaadin.XySystemMessageProvider;
import hu.csdivad.xy.vaadin.view.AccountDetailsView;
import hu.csdivad.xy.vaadin.view.AccountHistoryView;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SpringUI(path = "/netbank")
@PreserveOnRefresh
public class MainUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private UserDao userDao;
	@Autowired
	private User loggedInUser;
	private Calendar loginTime;
	private VerticalLayout pageLayout = new VerticalLayout();
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		VaadinService.getCurrent().setSystemMessagesProvider(new XySystemMessageProvider());
		loginTime = Calendar.getInstance();

		addStyleName("backColor");
		setContent(pageLayout);

		CssLayout contentLayout = new CssLayout();
		pageLayout.addComponents(createMenuBar(), contentLayout);
		navigator = new Navigator(this, contentLayout);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(AccountDetailsView.VIEW_NAME);
	}

	private Component createMenuBar() {
		HorizontalLayout menuLayout = new HorizontalLayout();
		menuLayout.setWidth("100%");

		MenuBar leftMenu = new MenuBar();
		leftMenu.setWidth("100%");
		leftMenu.setHeightUndefined();

		MenuBar.MenuItem accountMenu = leftMenu.addItem("Account", null);
		accountMenu.addItem("Account details", (item) -> navigator.navigateTo(AccountDetailsView.VIEW_NAME));
		accountMenu.addItem("Transaction history", (item) -> navigator.navigateTo(AccountHistoryView.VIEW_NAME));

		MenuBar rightMenu = new MenuBar();
		rightMenu.addItem("Logout", (item) -> logout());

		rightMenu.setSizeUndefined();
		menuLayout.addComponents(leftMenu, rightMenu);
		menuLayout.setExpandRatio(leftMenu, 1.0f);
		return menuLayout;
	}

	private void logout() {
		for (UI ui : getSession().getUIs()) {
			ui.getPage().open("", null);
		}
		SecurityContextHolder.clearContext();
		UI.getCurrent().getSession().close();
	}

	private void logLastLogin() {
		try {
			loggedInUser.setLastLogin(loginTime);
			userDao.updateUser(loggedInUser);
		} catch (Exception ex) {
			// TODO Logger?
			System.out.println("Failed to update lastLogin property.");
			ex.printStackTrace();
		}
	}

	@Override
	public void close() {
		logLastLogin();
		super.close();
	}

}
