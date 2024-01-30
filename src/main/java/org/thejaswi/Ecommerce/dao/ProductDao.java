package org.thejaswi.Ecommerce.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.thejaswi.Ecommerce.dto.Product;
import org.thejaswi.Ecommerce.repository.ProductRepository;


@Repository
public class ProductDao {

	@Autowired
	ProductRepository productRepository;

	public boolean checkName(String name) {
		return productRepository.existsByNmae(name);
	}

	public void save(Product product) {
		productRepository.save(product);
	}

	public List<Product> fetchAll() {
		return productRepository.findAll();
	}
}
