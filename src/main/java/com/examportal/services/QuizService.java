package com.examportal.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;

public interface QuizService {
	public GenericResponse addQuiz(Quiz quiz) throws Exception;

	public GenericResponse updateQuiz(Quiz quiz , long quizId);

	public Page<Quiz> getQuizzes(int page, int size);

	public Quiz getQuiz(Long quizId);

	public GenericResponse changeQuizStatus(Long id , boolean newStatus);
	
	public List<Quiz> getActiveQuizzes();

	public QuizDto convertQuizToQuizDto(Quiz quiz);
	

}
