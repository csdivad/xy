package hu.csdivad.xy.vaadin.view;

import org.vaadin.risto.formsender.FormSender;
import org.vaadin.risto.formsender.widgetset.client.shared.Method;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.Action;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import hu.csdivad.xy.spring.security.exception.AccountNotBelongToUserException;
import hu.csdivad.xy.spring.security.exception.MissingAccountNumberException;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends LoginForm implements View {
	private static final long serialVersionUID = 2992502147074865800L;
	public static final String VIEW_NAME = "login";

	private TextField accountNumberField = new TextField();
	Action action_ok = new ShortcutAction("Default key", ShortcutAction.KeyCode.ENTER, null);

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

	private Component createLoginDetailsForm(TextField usernameField, PasswordField passwordField, Button loginButton) {
		FormLayout loginDetailsForm = new FormLayout();
		loginDetailsForm.setSpacing(true);
		loginDetailsForm.setMargin(true);
		loginDetailsForm.setSizeUndefined();

		usernameField.setCaption("Ügyfélazonosító");
		usernameField.setIcon(FontAwesome.USER);
		usernameField.addValidator(new NullValidator("Hiányzó ügyfélazonosító", false));

		passwordField.setCaption("Jelszó");
		passwordField.setIcon(FontAwesome.LOCK);
		passwordField.addValidator(new NullValidator("Hiányzó jelszó", false));

		accountNumberField.setCaption("Számlaszám");
		accountNumberField.setIcon(FontAwesome.BOOK);
		accountNumberField.addValidator(new NullValidator("Hiányzó számlaszám", false));

		registerEnterKey(accountNumberField, usernameField, passwordField);
		loginButton.setCaption("Bejelentkezés");
		loginDetailsForm.addComponents(usernameField, passwordField, accountNumberField, loginButton);
		return loginDetailsForm;
	}

	private void registerEnterKey(AbstractComponent component, TextField usernameField, PasswordField passwordField) {
		component.addShortcutListener(new ShortcutListener("Enter", ShortcutAction.KeyCode.ENTER, null) {
			private static final long serialVersionUID = -1029061321445408387L;

			@Override
			public void handleAction(Object sender, Object target) {
				LoginView.this.login(usernameField.getValue(), passwordField.getValue());
			}
		});
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

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
