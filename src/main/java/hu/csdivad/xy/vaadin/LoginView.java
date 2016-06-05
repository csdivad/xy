package hu.csdivad.xy.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import hu.csdivad.xy.dao.UserDao;

public class LoginView extends LoginForm {

	@Autowired
	private UserDao userDao;
	private CheckBox safeBrowser = new CheckBox("This is a private computer");

	@Override
	protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
		VerticalLayout pageLayout = new VerticalLayout();
		pageLayout.setWidth("100%");
		Component loginDetailsForm = createLoginDetailsForm(userNameField, passwordField, loginButton);
		Component maintenanceMsg = createMainenanceMsg();
		pageLayout.addComponent(maintenanceMsg);
		pageLayout.addComponent(loginDetailsForm);
		pageLayout.setComponentAlignment(maintenanceMsg, Alignment.MIDDLE_CENTER);
		pageLayout.setComponentAlignment(loginDetailsForm, Alignment.MIDDLE_CENTER);
		return pageLayout;
	}

	@Override
	protected void login(String userName, String password) {
		super.login(userName, password);
	}

	private Component createLoginDetailsForm(TextField username, PasswordField password, Button login) {
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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
