package com.examportal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategory(Category cat);
}
