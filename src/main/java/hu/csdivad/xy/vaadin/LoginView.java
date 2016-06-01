package hu.csdivad.xy.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.csdivad.xy.dao.UserDao;

@SpringView(name = "login")
public class LoginView extends VerticalLayout implements View {

	@Autowired
	private UserDao userDao;
	private final TextField username = new TextField("User");
	private final TextField password = new TextField("Password");
	private final CheckBox safeBrowser = new CheckBox("This is a private computer");
	private final Button login = new Button("Login");
	private Navigator navigator;
	
	public LoginView() {
		init();
		registerButtonPressEvent();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
	}

	private void init() {
		setWidth("100%");
		Component loginDetailsForm = createLoginDetailsForm();
		Component maintenanceMsg = createMainenanceMsg();
		addComponent(maintenanceMsg);
		addComponent(loginDetailsForm);
		setComponentAlignment(maintenanceMsg, Alignment.MIDDLE_CENTER);
		setComponentAlignment(loginDetailsForm, Alignment.MIDDLE_CENTER);
	}

	private Component createLoginDetailsForm() {
		FormLayout loginDetailsForm = new FormLayout();
		loginDetailsForm.setSpacing(true);
		loginDetailsForm.setMargin(true);
		loginDetailsForm.setSizeUndefined();

		username.setIcon(FontAwesome.USER);
		username.setRequired(true);
		username.addValidator(new NullValidator("Missing username", false));

		password.setIcon(FontAwesome.LOCK);
		password.setRequired(true);
		password.addValidator(new NullValidator("Missing password", false));

		loginDetailsForm.addComponent(username);
		loginDetailsForm.addComponent(password);
		loginDetailsForm.addComponent(safeBrowser);
		loginDetailsForm.addComponent(login);
		return loginDetailsForm;
	}

	private Component createMainenanceMsg() {
		Label msg = new Label("Maintenance msgs");
		msg.setSizeUndefined();
		return msg;
	}

	private void registerButtonPressEvent() {
		login.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(navigator!=null) {
					navigator.navigateTo("session");
				}
			}
		});
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
