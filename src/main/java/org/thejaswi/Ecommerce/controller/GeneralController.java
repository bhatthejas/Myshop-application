package org.thejaswi.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thejaswi.Ecommerce.dto.Customer;
import org.thejaswi.Ecommerce.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GeneralController {
	
	@Autowired
	Customer customer;
	
	@Autowired
	CustomerService customerService;
	@GetMapping("/")
	public String loadHome() {
		return "Home";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("customer", customer);
		return "Signup";
	}

	@GetMapping("/signin")
	public String loadLogin() {
		return "Login";
	}

	@PostMapping("/signup")
	public String signup(@Valid Customer customer, BindingResult result) {
		if (result.hasErrors())
			return "Signup";
		else
			return customerService.save(customer, result);
	}

	@GetMapping("/send-otp/{id}")
	public String sendOtp(@PathVariable int id, ModelMap map) {
		return customerService.sendOtp(id,map);
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id,@RequestParam int otp,ModelMap map) {
		return customerService.verifyOtp(id,otp,map);
	}
	
	@GetMapping("/resend-otp/{id}")
	public String resendOtp(@PathVariable int id, ModelMap map) {
		return customerService.resendOtp(id,map);
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, ModelMap map, HttpSession session) {
		return customerService.login(email, password, map, session);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		session.setAttribute("successMessage", "Logout Success");
		return "redirect:/";
	}
}
