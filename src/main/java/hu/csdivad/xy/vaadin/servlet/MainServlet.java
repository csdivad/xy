//package hu.csdivad.xy.vaadin.servlet;
//
//import java.util.Calendar;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.vaadin.annotations.VaadinServletConfiguration;
//import com.vaadin.server.ServiceException;
//import com.vaadin.server.SessionDestroyEvent;
//import com.vaadin.server.SessionDestroyListener;
//import com.vaadin.server.SessionInitEvent;
//import com.vaadin.server.SessionInitListener;
//import com.vaadin.spring.server.SpringVaadinServlet;
//
//import hu.csdivad.xy.bean.User;
//import hu.csdivad.xy.dao.UserDao;
//import hu.csdivad.xy.vaadin.ui.MainUI;
//
//@WebServlet(urlPatterns = { "/vaadin/*", "/VAADIN/*" })
//@VaadinServletConfiguration(productionMode = false, widgetset = "hu.csdivad.xy.vaadin.XyWidgetSet", ui = MainUI.class)
//public class MainServlet extends SpringVaadinServlet implements SessionInitListener, SessionDestroyListener {
//	private Calendar sessionCreationTime;
//
//	@Override
//	protected void servletInitialized() throws ServletException {
//		super.servletInitialized();
//		getService().addSessionInitListener(this);
//		getService().addSessionDestroyListener(this);
//	}
//
//	@Override
//	public void sessionInit(SessionInitEvent event) throws ServiceException {
//		sessionCreationTime = Calendar.getInstance();
//	}
//
//	/*
//	 * Updates lastLogin property of the user. Is there a better way for this?
//	 * This class doesn't seem like a spring managed bean, so no di. Is it
//	 * possible to handle end of session event from inside a UI?
//	 */
//	@Override
//	public void sessionDestroy(SessionDestroyEvent event) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof User) {
//			User user = (User) principal;
//			ApplicationContext applicationContext = WebApplicationContextUtils
//					.getWebApplicationContext(getServletContext());
//			UserDao userDao = applicationContext.getBean(UserDao.class);
//			user.setLastLogin(sessionCreationTime);
//			userDao.updateUser(user);
//		}
//	}
//
//}