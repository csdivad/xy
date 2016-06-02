package hu.csdivad.xy.vaadin;

import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SpringView(name = "main")
public class MainView extends VerticalLayout implements View, ClickListener {

	private Button logout = new Button("Logout");

	public MainView() {
		init();
	}

	private void init() {
		logout.addClickListener(this);
		addComponent(logout);

	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// if (navigator != null) {
		// navigator.navigateTo("login");
		// }

		SecurityContextHolder.clearContext();
		UI.getCurrent().getSession().close();
		Page.getCurrent().open("main", null);
		// Page.getCurrent().open("j_spring_security_logout", null);
	}

}
