package hu.csdivad.xy.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

import hu.csdivad.xy.vaadin.view.LoginView;

@Theme("mytheme")
@SpringUI(path = "/login")
public class LoginUI extends UI {
	private static final long serialVersionUID = 4269249709825124375L;

	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		this.addStyleName("backColor");

		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(LoginView.VIEW_NAME);
	}

}
