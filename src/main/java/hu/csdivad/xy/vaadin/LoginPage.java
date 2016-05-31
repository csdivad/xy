package hu.csdivad.xy.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
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

@Theme("mytheme")
@SpringUI(path="/vaadin")
@SpringComponent
public class LoginPage extends UI {
//TODO: View + Navigation
	
	@Autowired
	private UserDao userDao;
	private List<User> users;
	private final VerticalLayout vLayout = new VerticalLayout();

	@Override
	protected void init(VaadinRequest request) {
		//userDao = new UserDaoImpl();
		this.addStyleName("backColorGrey");
		vLayout.setSizeFull();
		
		
		users = userDao.listAll();
		
		for(User u : users) {
			vLayout.addComponent(new Label(u.getUsername()));
		}
		
		Component loginForm = createLoginView();
		vLayout.addComponent(createMenuBar());
		vLayout.addComponent(loginForm);
		vLayout.setExpandRatio(loginForm, 1);
		
		this.setContent(vLayout);

	}
	
	private Component createLoginView() {
		LoginView loginView = new LoginView(userDao);
		loginView.setSizeUndefined();
		
		Component maintenanceMsg = createMainenanceMsg();
		maintenanceMsg.setSizeUndefined();
		
		GridLayout loginPageLayout = new GridLayout(1,2);
		loginPageLayout.addComponent(maintenanceMsg, 0, 0);
		loginPageLayout.addComponent(loginView, 0, 1);
		loginPageLayout.setWidth("100%");
		loginPageLayout.setComponentAlignment(maintenanceMsg, Alignment.TOP_CENTER);
		loginPageLayout.setComponentAlignment(loginView, Alignment.MIDDLE_CENTER);
		
		
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
