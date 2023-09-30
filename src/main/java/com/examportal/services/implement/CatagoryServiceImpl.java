package com.examportal.services.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.model.exam.Category;
import com.examportal.repo.CategoryRepository;
import com.examportal.services.CategoryService;
@Service
public class CatagoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public Category addCategory(Category category) throws Exception {
		if(category.getTitle() == null) {
			throw new Exception("Please enter Tital");
		}
		
		else if (category.getIsActive() == null) {
			throw new Exception("Active it");
		}
		else {
			
		}
		
	
		return categoryRepository.save(category );
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}
	@Override
	public Page<Category> getCategories(int page, int size) {
		
		Pageable pageable1 =PageRequest.of(page, size);
		
		Page<Category> category ;
		
		category =  categoryRepository.findAll(pageable1);
		return category;
	}
	

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public Category updateStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	

	/*@Override
	public Category addCategory(Category category) {
		
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return this.categoryRepository.save(category);
	}

	@Override
	public List<Category> getCategories() {
		
		return new LinkedHashSet<> (this.categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		
		return this.categoryRepository.findById(categoryId).get();
	}

	@Override
	public void delete(Long categoryId) {
		this.categoryRepository.deleteById(categoryId);
		
	}*/

}
