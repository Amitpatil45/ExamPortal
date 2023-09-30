package com.examportal.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.repo.QuestionRepository;

public interface QuestionService {
	
	
	public Question addQuestion(Question question) throws Exception;

	public Question updateQuestion(Question question);

	public Page<Question> getQuestions(int page, int size);

	public Question getQuestion(Long questionId);

	//public Set<Question> getQuestionOfQuiz(Quiz quiz);

	

}
