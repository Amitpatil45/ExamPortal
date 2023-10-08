package com.examportal.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findAll(Pageable pageable);
	List<Category> findByIsActiveTrue();
}
