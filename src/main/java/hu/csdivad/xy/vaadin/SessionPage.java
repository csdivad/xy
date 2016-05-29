package hu.csdivad.xy.vaadin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class SessionPage extends UI {

	@Override
	protected void init(VaadinRequest request) {
		this.setContent(createLogout());
	}

	private Component createLogout() {
		Button logout = new Button("Logout");
		
		
		
		return logout;
	}

}
