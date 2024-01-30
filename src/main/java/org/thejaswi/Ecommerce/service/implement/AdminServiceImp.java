package org.thejaswi.Ecommerce.service.implement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.thejaswi.Ecommerce.dao.ProductDao;
import org.thejaswi.Ecommerce.dto.Customer;
import org.thejaswi.Ecommerce.dto.Product;
import org.thejaswi.Ecommerce.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	Product product;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public String loadDashboard(HttpSession session) {
		Customer customer=(Customer) session.getAttribute("customer");
		if(customer==null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		}
		else {
			if(customer.getRole().equals("ADMIN"))
				return "AdminDashBoard";
			else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String loadAddProduct(HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				map.put("product", product);
				return "AddProduct";
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String addProduct(Product product, BindingResult result, HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				if (productDao.checkName(product.getName()))
					result.rejectValue("name", "error.name", "Product with Same Name Already Exists");

				if (result.hasErrors())
					return "AddProduct";
				else {
//					try {
//						byte[] image = new byte[picture.getInputStream().available()];
//						picture.getInputStream().read(image);
//
//						product.setImage(image);
					product.setImagePath("/images/" + product.getName() + ".jpg");
					productDao.save(product);

					File file = new File("src/main/resources/static/images");
					if (!file.isDirectory())
						file.mkdir();

					try {
						Files.write(Paths.get("src/main/resources/static/images", product.getName() + ".jpg"),
								picture.getBytes());
					} catch (IOException e) {
						session.setAttribute("failMessage", "You are Unauthorized to access his URL");
						return "redirect:/";
					}
					session.setAttribute("successMessage", "Product Added Success");
					return "redirect:/admin";

//					} catch (IOException e) {
//						session.setAttribute("failMessage", "You are Unauthorized to access his URL");
//						return "redirect:/";
//					}
				}
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String manageproducts(HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				List<Product> products = productDao.fetchAll();
				if (products.isEmpty()) {
					session.setAttribute("failMessage", "No Products Present");
					return "redirect:/admin";
				} else {
					map.put("products", products);
					return "ManageProducts";
				}
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	} 

}
