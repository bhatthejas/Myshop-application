package org.thejaswi.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thejaswi.Ecommerce.dto.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	boolean existsByNmae(String name);

}
