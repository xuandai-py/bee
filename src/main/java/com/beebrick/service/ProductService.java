package com.beebrick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.beebrick.entity.Product;

public interface ProductService {

	void save(Product product);
	
	void delete(Integer productID);

	Optional<Product> findById(Integer productID);
	List<Product> findAll();

	Optional<Product> findProductById(Integer productID);

	Page<Product> findPaginated(int pageNo, int pageSize);
}
