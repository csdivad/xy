package hu.csdivad.xy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(value = { "/asd" }, method = RequestMethod.GET)
	public String loginGet(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/asd" }, method = RequestMethod.POST)
	public String loginPost(@RequestParam(value = "customerId", required = true) String customerId,
			@RequestParam(value = "accountNumber", required = true) int accountNumber,
			@RequestParam(value = "password", required = true) String password) {
		System.out.println(password);
		return "login";
	}

}
