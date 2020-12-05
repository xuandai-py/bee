package com.beebrick.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beebrick.entity.Category;
import com.beebrick.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "admin/category")
	public String index(Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("categories", categories);
		return "admin/category/index";
	}

	@RequestMapping(value = "admin/category/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "admin/category/add";
	}

	@RequestMapping(value = "admin/category/save", method = RequestMethod.POST)
	public String save(@Valid Category category, BindingResult bindingResult, Model model, String categoryName) {
		List<Category> list = categoryService.findByName(categoryName);
		if(!list.isEmpty()) {
			category.setCategoryName(null);
			model.addAttribute("message", "Category Name is already exists!");
			return "admin/category/add";
		}
		if (bindingResult.hasErrors()) {
			return "admin/category/add";
		} else {
			categoryService.save(category);
			return "redirect:/admin/category";
		}
	}

	@RequestMapping(value = "admin/category/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("categoryID") Integer categoryID, Model model) {
		Optional<Category> edit = categoryService.findById(categoryID);
		edit.ifPresent(category -> model.addAttribute("category", category));
		return "admin/category/edit";
	}

	@RequestMapping(value = "admin/category/update", method = RequestMethod.POST)
	public String update(@Valid Category category, BindingResult bindingResult, Model model, String categoryName) {
		List<Category> list = categoryService.findByName(categoryName);
		if(!list.isEmpty()) {
			category.setCategoryName(null);
			model.addAttribute("message", "Category Name is already exists!");
			return "admin/category/add";
		}
		if (bindingResult.hasErrors()) {
			return "admin/category/add";
		} else {
			categoryService.save(category);
			return "redirect:/admin/category";
		}
	}

	@RequestMapping(value = "admin/category/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("categoryID") Integer categoryID, Model model) {
		categoryService.delete(categoryID);
		return "redirect:/admin/category";
	}
}
