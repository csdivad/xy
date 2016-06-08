package hu.csdivad.xy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.csdivad.xy.dao.UserDao;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = { "/asd" }, method = RequestMethod.GET)
	public String loginGet(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/asd" }, method = RequestMethod.POST)
	public String loginPost(
			@RequestParam(value = "customerId", required = true) String customerId, 
			@RequestParam(value = "accountNumber", required = true) int accountNumber,
			@RequestParam(value = "password", required = true) String password) {
		System.out.println(password);
		return "login";
	}
	
}
