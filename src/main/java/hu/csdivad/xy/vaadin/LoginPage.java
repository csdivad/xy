package hu.csdivad.xy.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


public class LoginPage extends UI {

	private static final long serialVersionUID = 3347650577613856999L;

	List<User> users;

	@Autowired
	UserDao userDao;

	private final VerticalLayout vLayout = new VerticalLayout();
	private final TabSheet tabSheet = new TabSheet();

	@Override
	protected void init(VaadinRequest request) {
		//userDao = new UserDaoImpl();
		this.addStyleName("backColorGrey");
		vLayout.setSizeFull();
		
		
		users = userDao.listAll();
		
		for(User u : users) {
			vLayout.addComponent(new Label(u.getUsername()));
		}
		
//		Component loginForm = createLoginView();
//		vLayout.addComponent(createMenuBar());
//		vLayout.addComponent(loginForm);
//		vLayout.setExpandRatio(loginForm, 1);
		
		this.setContent(vLayout);

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
