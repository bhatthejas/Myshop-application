package org.thejaswi.Ecommerce.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.thejaswi.Ecommerce.dto.Customer;

import jakarta.servlet.http.HttpSession;

public interface CustomerService {
	String save(Customer customer, BindingResult result);

	String sendOtp(int id, ModelMap map);

	String verifyOtp(int id, int otp, ModelMap map);

	String resendOtp(int id, ModelMap map);

	String login(String email, String password, ModelMap map, HttpSession session);
}
