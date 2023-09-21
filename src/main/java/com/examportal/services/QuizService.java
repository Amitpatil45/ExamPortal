package com.examportal.services;

import java.util.*;
import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;

public interface QuizService {
	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public Set<Quiz> getQuizzes();

	public Quiz getQuiz(Long quizId);

	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesOfCategory(Category cat);
}
