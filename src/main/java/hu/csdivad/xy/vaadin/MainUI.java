package hu.csdivad.xy.vaadin;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

@Theme("mytheme")
@SpringUI(path = "/*")
@UIScope
@PreserveOnRefresh
public class MainUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private UserDao userDao;
	private Calendar loginTime = Calendar.getInstance();
	private User user;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName("backColor");
		setContent(new Label(viewProvider.getClass().getName()));

		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(viewProvider);
		navigator.navigateTo("main");
	}

}
