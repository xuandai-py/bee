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

import com.beebrick.entity.Blog;
import com.beebrick.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "admin/blog")
	public String index(Model model) {
		List<Blog> blogs = blogService.getAll();
		model.addAttribute("blogs", blogs);
		return "admin/blog/index";
	}

	@RequestMapping(value = "admin/blog/add")
	public String add(Model model) {
		model.addAttribute("blog", new Blog());
		return "admin/blog/add";
	}

	@RequestMapping(value = "admin/blog/save", method = RequestMethod.POST)
	public String save(@Valid Blog blog, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/blog/add";
		} else {
			blogService.save(blog);
			return "redirect:/admin/blog";
		}
	}

	@RequestMapping(value = "admin/blog/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("blogID") Integer blogID, Model model) {
		Optional<Blog> edit = blogService.findById(blogID);
		edit.ifPresent(blog -> model.addAttribute("blog", blog));
		return "admin/blog/edit";
	}

	@RequestMapping(value = "admin/blog/update", method = RequestMethod.POST)
	public String update(@Valid Blog blog, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/blog/edit";
		} else {
			blogService.save(blog);
			return "redirect:/admin/blog";
		}
	}

	@RequestMapping(value = "admin/blog/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("blogID") Integer blogID, Model model) {
		blogService.delete(blogID);
		return "redirect:/admin/blog";
	}
}
