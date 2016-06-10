package hu.csdivad.xy.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import hu.csdivad.xy.vaadin.view.AccountDetailsView;
import hu.csdivad.xy.vaadin.view.TransactionHistoryView;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SpringUI(path = "/netbank")
@PreserveOnRefresh
public class MainUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;
	private VerticalLayout pageLayout = new VerticalLayout();
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
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
		accountMenu.addItem("Transaction history", (item) -> navigator.navigateTo(TransactionHistoryView.VIEW_NAME));

		MenuBar rightMenu = new MenuBar();
		rightMenu.addItem("Logout", (item) -> logout());

		rightMenu.setSizeUndefined();
		menuLayout.addComponents(leftMenu, rightMenu);
		menuLayout.setExpandRatio(leftMenu, 1.0f);
		return menuLayout;
	}

	private void logout() {
		SecurityContextHolder.clearContext();

		for (UI ui : UI.getCurrent().getSession().getUIs())
			ui.access(() -> {
				ui.getPage().open("", null); // .setLocation("/logout.html");
			});
		UI.getCurrent().getSession().close();
	}

}
