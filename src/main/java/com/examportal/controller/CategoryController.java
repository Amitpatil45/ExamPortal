package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.exam.Category;
import com.examportal.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// add
	@PostMapping("/")
	public ResponseEntity<Category> addcategory(@RequestBody Category category) throws Exception {
		Category category1 = categoryService.addCategory(category);
		return ResponseEntity.ok(category1);

	}

	// get

	@GetMapping("/{categoryId}")
	public Category category(@PathVariable("categoryId") Long categoryId) {
		return categoryService.getCategory(categoryId);

	}

	/*@GetMapping("/")
	public ResponseEntity<?> getCategories() {
		return ResponseEntity.ok(categoryService.getCategories(1,5));

	}*/
	
	@GetMapping("/all-categories")
	public Page<Category> getAllCategories(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "20") int size){
		return categoryService.getCategories(page, size);
	}

	// update
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);

	}

	

}
