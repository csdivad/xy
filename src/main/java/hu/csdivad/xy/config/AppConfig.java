package hu.csdivad.xy.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.spring.VaadinConfiguration;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;

import hu.csdivad.xy.vaadin.LoginPage;

@Configuration
@ComponentScan({ "hu.csdivad.xy.*" })
@EnableVaadin
public class AppConfig {
	
//xmlconfig
//	@WebServlet(urlPatterns = { "/vaadin/*", "/VAADIN/*" })
//	@VaadinServletConfiguration(productionMode = false, ui = LoginPage.class, widgetset = "com.vaadin.DefaultWidgetSet")
//	public static class Servlet extends SpringVaadinServlet {
//	}
	
	@Bean
	public SessionFactory buildSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			throw new ExceptionInInitializerError(e);
		}
	}

}
