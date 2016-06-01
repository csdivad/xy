package hu.csdivad.xy.vaadin;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SpringView(name = "session")
public class SessionView extends VerticalLayout implements View, ClickListener {

	private Navigator navigator;
	private Button logout = new Button("Logout");
	
	public SessionView() {
		init();
	}

	private void init() {
		logout.addClickListener(this);
		addComponent(logout);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (navigator != null) {
			navigator.navigateTo("login");
		}		
	}

}
