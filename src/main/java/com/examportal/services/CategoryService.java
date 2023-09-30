package com.examportal.services;

import org.springframework.data.domain.Page;

import com.examportal.model.exam.Category;


public interface CategoryService {
	public Category addCategory(Category category) throws Exception;

	public Category updateCategory(Category category);


	public Category getCategory(Long categoryId);

	public Category updateStatus();

	Page<Category> getCategories(int page, int size);



}
