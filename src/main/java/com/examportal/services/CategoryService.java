package com.examportal.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Category;


public interface CategoryService {
	public GenericResponse addCategory(Category category) throws Exception;

	public GenericResponse updateCategory(Category category, int cid);


	public Category getCategory(Long categoryId);

	public GenericResponse updateStatus();

	Page<Category> getCategories(int page, int size);
	
	List<Category> getallcategories(Category category);



}
