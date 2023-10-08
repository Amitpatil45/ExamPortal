package com.examportal.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.exception.DataValidationException;
import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Category;
import com.examportal.repo.CategoryRepository;
import com.examportal.services.CategoryService;

@Service
public class CatagoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public GenericResponse addCategory(Category category) throws Exception {
		if (category.getTitle() == null || category.getTitle().isBlank()) {
			throw new DataValidationException("please fill title");
		}

		else if (category.getIsActive() == null) {
			throw new DataValidationException("please give permission Active or Deactive ");
		} else {
			categoryRepository.save(category);

			return new GenericResponse(201, "Created Succesfully");
		}

	}

	@Override
	public GenericResponse updateCategory(Category category, long cid) {
		Optional<Category> optionalCategory = categoryRepository.findById(cid);
		if (optionalCategory.isPresent()) {
			Category presentCategory = optionalCategory.get();
			presentCategory.setTitle(category.getTitle());
			presentCategory.setDescription(category.getDescription());
			return new GenericResponse(202, "Updated Succesfully");
		}

		else {
			return new GenericResponse(404, "ID NOT FOUND");
		}

	}

	@Override
	public Page<Category> getCategories(int page, int size) {

		Pageable pageable1 = PageRequest.of(page, size);

		Page<Category> category;

		category = categoryRepository.findAll(pageable1);
		return category;
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public GenericResponse changeCategoryStatus(Long categoryId, Boolean newStatus) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setIsActive(newStatus);
			categoryRepository.save(category);

			return new GenericResponse(200, "Category status updated successfully.");
		}
		return new GenericResponse(404, "Category not found");

	}

	@Override
	public List<Category> getActiveCategories() {
		return categoryRepository.findByIsActiveTrue();
	}

	/*
	 * @Override public Category addCategory(Category category) {
	 * 
	 * return this.categoryRepository.save(category); }
	 * 
	 * @Override public Category updateCategory(Category category) {
	 * 
	 * return this.categoryRepository.save(category); }
	 * 
	 * @Override public List<Category> getCategories() {
	 * 
	 * return new LinkedHashSet<> (this.categoryRepository.findAll()); }
	 * 
	 * @Override public Category getCategory(Long categoryId) {
	 * 
	 * return this.categoryRepository.findById(categoryId).get(); }
	 * 
	 * @Override public void delete(Long categoryId) {
	 * this.categoryRepository.deleteById(categoryId);
	 * 
	 * }
	 */

}
