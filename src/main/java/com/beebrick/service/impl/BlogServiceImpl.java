package com.beebrick.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.beebrick.entity.Blog;
import com.beebrick.repository.BlogRepository;
import com.beebrick.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public List<Blog> getAll() {
		return blogRepository.getAll();
	}

	@Override
	public void save(Blog blog) {
		blogRepository.save(blog);
	}

	@Override
	public void delete(Integer blogID) {
		blogRepository.delete(blogID);
	}

	@Override
	public Optional<Blog> findById(Integer blogID) {
		return blogRepository.findById(blogID);
	}
}
