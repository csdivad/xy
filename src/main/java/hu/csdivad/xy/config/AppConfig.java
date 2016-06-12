package hu.csdivad.xy.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.UIScope;

import hu.csdivad.xy.bean.Account;
import hu.csdivad.xy.bean.User;
import hu.csdivad.xy.dao.AccountDao;
import hu.csdivad.xy.spring.security.XyWebAuthenticationDetails;

@Configuration
@EnableVaadin
public class AppConfig {

	@Autowired
	private AccountDao accountDao;

	@Bean
	public SessionFactory buildSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("\n" + e.getMessage() + "\n");
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(
					"|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			StandardServiceRegistryBuilder.destroy(registry);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// TODO ASK. Are these appropriate? Safe? Probably not.
	@Bean
	@UIScope
	public User getLoggedInUserFromContext() throws SessionIsNotAuthenticatedException {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new SessionIsNotAuthenticatedException();
		}
	}

	@Bean
	@UIScope
	public Account getLoggedInAccountFromContext() throws SessionIsNotAuthenticatedException {
		try {
			return accountDao.findAccountById(
					((XyWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails())
							.getAccountNumber());
		} catch (Exception e) {
			throw new SessionIsNotAuthenticatedException();
		}
	}

}
