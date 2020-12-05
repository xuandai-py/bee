package com.beebrick.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Product;
import com.beebrick.repository.ProductRepository;
import com.beebrick.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.getAll();
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void delete(Integer productID) {
		productRepository.delete(productID);
	}

	@Override
	public Optional<Product> findById(Integer productID) {
		return productRepository.findById(productID);
	}

	@Override
	public List<Product> getAllRandom() {
		return productRepository.getAllRamdom();
	}


}
