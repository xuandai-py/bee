package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Category;
import com.beebrick.repository.CategoryRepository;
import com.beebrick.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		return categoryRepository.getAll();
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void delete(Integer categoryID) {
		categoryRepository.delete(categoryID);
	}

	@Override
	public Optional<Category> findById(Integer categoryID) {
		return categoryRepository.findById(categoryID);
	}

	@Override
	public List<Category> findByName(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}
}
