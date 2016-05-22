package hu.csdivad.xy.vaadin;

import java.util.List;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

public class UserUI extends UI {

	private static final long serialVersionUID = 3347650577613856999L;
	
	List<User> users;
	UserDao userDao;
	
	@Override
	protected void init(VaadinRequest request) {
		this.setContent(new Label(String.valueOf(userDao)));
		
	}
	
	
}
