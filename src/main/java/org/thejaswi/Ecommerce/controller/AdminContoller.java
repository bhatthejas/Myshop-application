package org.thejaswi.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thejaswi.Ecommerce.dto.Product;
import org.thejaswi.Ecommerce.service.AdminService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminContoller {

	@Autowired
	AdminService adminService;

	@GetMapping
	public String loadDashboard(HttpSession session) {
		return adminService.loadDashboard(session);
	}

	@GetMapping("/add-product")
	public String laodAddProduct(HttpSession session, ModelMap map) {
		return adminService.loadAddProduct(session, map);
	}

	@GetMapping("/add-product")
	public String addProduct(Product product, BindingResult result, HttpSession session, ModelMap map) {
		return adminService.addProduct(product, result, session, map);
	}

	@GetMapping("/manage-products")
	public String manageProducts(HttpSession session, ModelMap map) {
		return adminService.manageproducts(session, map);
	}

}
