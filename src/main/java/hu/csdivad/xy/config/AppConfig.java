package hu.csdivad.xy.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.servlet.annotation.WebServlet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.server.VaadinCDIServlet;

import hu.csdivad.xy.vaadin.LoginPage;

@ApplicationScoped
public class AppConfig {

	@WebServlet(urlPatterns = { "/vaadin/*", "/VAADIN/*" })
	@VaadinServletConfiguration(productionMode = false, ui = LoginPage.class, widgetset = "com.vaadin.DefaultWidgetSet")
	public static class Servlet extends VaadinCDIServlet {
	}

	@Default
	@Produces
	private static SessionFactory buildSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			throw new ExceptionInInitializerError(e);
		}
	}

}
