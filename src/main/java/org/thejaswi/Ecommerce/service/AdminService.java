package org.thejaswi.Ecommerce.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.thejaswi.Ecommerce.dto.Product;

import jakarta.servlet.http.HttpSession;

public interface AdminService {

	String loadDashboard(HttpSession session);

	String loadAddProduct(HttpSession session, ModelMap map);

	String addProduct(Product product, BindingResult result, HttpSession session, ModelMap map);

	String manageproducts(HttpSession session, ModelMap map);

}
