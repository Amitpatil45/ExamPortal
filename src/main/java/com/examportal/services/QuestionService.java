package com.examportal.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.repo.QuestionRepository;

public interface QuestionService {
	
	
	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public Set<Question> getQuestionOfQuiz(Quiz quiz);

	public void deleteQuestion(Long qid);;
}
