package hu.csdivad.xy.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;
import hu.csdivad.xy.dao.impl.UserDaoImpl;

@Theme("mytheme")
public class LoginPage extends UI {

	private static final long serialVersionUID = 3347650577613856999L;

	List<User> users;

	// TODO dependency injection ?
	@Autowired
	@Qualifier("firstUserDaoImpl")
	UserDao userDao;

	private final VerticalLayout vLayout = new VerticalLayout();
	private final TabSheet tabSheet = new TabSheet();

	@Override
	protected void init(VaadinRequest request) {
		userDao = new UserDaoImpl();
		this.addStyleName("backColorGrey");
		vLayout.setSizeFull();
		
		Component loginForm = createLoginForm();
		vLayout.addComponent(createMenuBar());
		vLayout.addComponent(loginForm);
		vLayout.setExpandRatio(loginForm, 1);
		
		this.setContent(vLayout);

	}
	
	private Component createLoginForm() {
		LoginForm loginForm = new LoginForm(userDao);
		loginForm.setSizeUndefined();
		
		Component maintenanceMsg = createMainenanceMsg();
		maintenanceMsg.setSizeUndefined();
		
		GridLayout loginPageLayout = new GridLayout(1,2);
		loginPageLayout.addComponent(maintenanceMsg, 0, 0);
		loginPageLayout.addComponent(loginForm, 0, 1);
		loginPageLayout.setWidth("100%");
		loginPageLayout.setComponentAlignment(maintenanceMsg, Alignment.TOP_CENTER);
		loginPageLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		
		
		return loginPageLayout;
	}

	private Component createMainenanceMsg() {
		Label msg = new Label("Maintenance msgs");
		return msg;
	}

	private Component createMenuBar() {
		MenuBar menubar = new MenuBar();
		menubar.setWidth("100%");
		menubar.setHeightUndefined();
		
		MenuBar.MenuItem empMenu = menubar.addItem("File", null);
		empMenu.addItem("Employee", (selectedItem) -> vLayout.addComponent(new Label("Emppq")));

		empMenu.addItem("Department", (selectedItem) -> vLayout.addComponent(new Label("Depp")));

		return menubar;
	}

}
