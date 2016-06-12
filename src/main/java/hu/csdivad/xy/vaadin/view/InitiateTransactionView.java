package hu.csdivad.xy.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.UserDao;

//TODO VerticalLayout vs CustomComponent+setCompositonRoot
@SpringView(name = InitiateTransactionView.VIEW_NAME)
public class InitiateTransactionView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "initiate-transaction";
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private Account loggedInAccount;
	@Autowired
	private User user;

	public InitiateTransactionView() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
