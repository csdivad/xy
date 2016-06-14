package hu.csdivad.xy.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.risto.formsender.FormSender;
import org.vaadin.risto.formsender.widgetset.client.shared.Method;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import hu.csdivad.xy.dao.UserDao;
import hu.csdivad.xy.spring.security.exception.AccountNotBelongToUserException;
import hu.csdivad.xy.spring.security.exception.MissingAccountNumberException;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends LoginForm implements View {
	private static final long serialVersionUID = 2992502147074865800L;
	public static final String VIEW_NAME = "login";

	@Autowired
	private UserDao userDao;
	private TextField accountNumberField = new TextField();

	@Override
	protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
		setSizeFull();
		VerticalLayout pageLayout = new VerticalLayout();
		pageLayout.setWidth("100%");
		Component loginDetailsForm = createLoginDetailsForm(userNameField, passwordField, loginButton);
		Component maintenanceMsg = createAdditionalMsgs();
		pageLayout.addComponent(maintenanceMsg);
		pageLayout.addComponent(loginDetailsForm);
		pageLayout.setComponentAlignment(maintenanceMsg, Alignment.MIDDLE_CENTER);
		pageLayout.setComponentAlignment(loginDetailsForm, Alignment.MIDDLE_CENTER);
		return pageLayout;
	}

	@Override
	protected void login(String userName, String password) {
		super.login(userName, password);

		FormSender sender = new FormSender();
		sender.setFormAction("/xy/j_spring_security_check");
		sender.setFormMethod(Method.POST);
		sender.addValue("username", userName);
		sender.addValue("password", password);
		sender.addValue("account-number", accountNumberField.getValue());
		sender.setFormTarget("_top");
		sender.extend(getUI());
		sender.submit();

	}

	private Component createLoginDetailsForm(TextField username, PasswordField password, Button login) {
		FormLayout loginDetailsForm = new FormLayout();
		loginDetailsForm.setSpacing(true);
		loginDetailsForm.setMargin(true);
		loginDetailsForm.setSizeUndefined();

		username.setCaption("Ügyfélazonosító");
		username.setIcon(FontAwesome.USER);
		username.addValidator(new NullValidator("Hiányzó ügyfélazonosító", false));

		password.setCaption("Jelszó");
		password.setIcon(FontAwesome.LOCK);
		password.addValidator(new NullValidator("Hiányzó jelszó", false));

		accountNumberField.setCaption("Számlaszám");
		accountNumberField.setIcon(FontAwesome.BOOK);
		accountNumberField.addValidator(new NullValidator("Hiányzó számlaszám", false));

		loginDetailsForm.addComponents(username, password, accountNumberField, login);
		return loginDetailsForm;
	}

	private Component createAdditionalMsgs() {
		VerticalLayout msgLayout = new VerticalLayout();

		Object springException = getUI().getSession().getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (springException instanceof org.springframework.security.authentication.BadCredentialsException) {
			msgLayout.addComponent(new Label("Hibás ügyfélazonosító vagy jelszó!"));
		}
		if (springException instanceof AccountNotBelongToUserException
				|| springException instanceof MissingAccountNumberException) {
			msgLayout.addComponent(new Label("Hibás számlaszám!"));
		}
		msgLayout.setSizeUndefined();
		return msgLayout;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
