package com.examportal.services;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Quiz;

public interface QuizService {
	public GenericResponse addQuiz(Quiz quiz) throws Exception;

	public GenericResponse updateQuiz(Quiz quiz);

	public Page<Quiz> getQuizzes(int page, int size);

	public Quiz getQuiz(Long quizId);

	// public List<Quiz> getQuizzesOfCategory(Category cat);

}
