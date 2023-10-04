package com.examportal.services.implement;

import org.springframework.data.domain.Pageable;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.examportal.exception.DataValidationException;
import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;
import com.examportal.repo.QuizRepository;
import com.examportal.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;

	@Override
	public GenericResponse addQuiz(Quiz quiz) throws Exception {
		if (quiz.getQuestions() == null) {
			throw new DataValidationException("Please Fill the Questions");

		} else if (quiz.getCategory() == null) {
			throw new DataValidationException("Please Fill the Category");

		}
		quizRepository.save(quiz);
		return new GenericResponse(201, "Created Succesfully");

	}

	@Override
	public GenericResponse updateQuiz(Quiz quiz , int quizId) {
		quiz.setQid(quizId);
		quizRepository.save(quiz);
		return new GenericResponse(202, "Updated Succesfully");
	}

	@Override
	public Page<Quiz> getQuizzes(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Quiz> quiz;

		quiz = quizRepository.findAll(pageable);

		return quiz;
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		Quiz quiz = new Quiz();
		if (quiz.isActive() == true) {
			return new ArrayList<Quiz>(quizRepository.findAll());
			
		}
		return null;
	}
	
	

	/*
	 * @Override public GenericResponse addQuiz(Quiz quiz) throws Exception {
	 * if(quiz.getQuestions()==null) { throw new
	 * DataValidationException("Please Fill the Questions");
	 * 
	 * }else if (quiz.getCategory()==null) { throw new
	 * DataValidationException("Please Fill the Category");
	 * 
	 * }else { quizRepository.save(quiz); return new
	 * GenericResponse(201,"Created Succesfully"); }
	 * 
	 * }
	 * 
	 * @Override public GenericResponse updateQuiz(Quiz quiz) { return
	 * this.quizRepository.save(quiz); }
	 * 
	 * @Override public Set<Quiz> getQuizzes() { return new LinkedHashSet<>(
	 * this.quizRepository.findAll()); }
	 * 
	 * @Override public Quiz getQuiz(Long quizId) { }
	 * 
	 * 
	 * 
	 * @Override public List<Quiz> getQuizzesOfCategory(Category cat) { return
	 * this.quizRepository.findByCategory(cat); }
	 */

}
