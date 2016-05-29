package hu.csdivad.xy.vaadin;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import hu.csdivad.xy.dao.UserDao;

public class LoginForm extends FormLayout {
	
	private final UserDao userDao;
	
	public LoginForm(UserDao userDao) {
		this.userDao = userDao;
		init();
		//TODO Form form;
	}

	private void init() {
		setSpacing(true);
		setMargin(true);
		
		TextField username = new TextField("User");
		TextField password = new TextField("Password");
		CheckBox safeBrowser = new CheckBox("This is a private computer");
		Button login = new Button("Login");
		
		username.setIcon(FontAwesome.USER);
		username.setRequired(true);
		username.addValidator(new NullValidator("Missing username", false));
		
		password.setIcon(FontAwesome.LOCK);
		password.setRequired(true);
		password.addValidator(new NullValidator("Missing password", false));
		
		addComponent(username);
		addComponent(password);
		addComponent(safeBrowser);
		addComponent(login); 
	}
	
}
