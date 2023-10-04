package com.examportal.services.implement;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.exception.DataValidationException;
import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Question;
import com.examportal.repo.QuestionRepository;
import com.examportal.services.QuestionService;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver.Unique;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public GenericResponse addQuestion(Question question) throws Exception {
		if (question.getContent() == null || question.getContent().isBlank()) {
			throw new DataValidationException("Enter Correct Question");
			
		} 
		
		System.out.println(question.getOption1());
		if (question.getOption1() == null || question.getOption1().isBlank()) {
			
			throw new DataValidationException("Enter Correct opition 1");

		}  if (question.getOption2() == null || question.getOption2().isBlank()) {
			throw new DataValidationException("Enter  opition 2");

		}  if (question.getOption3() == null || question.getOption3().isBlank()) {
			throw new DataValidationException("Enter  opition 3");

		}  if (question.getOption4() == null || question.getOption4().isBlank()) {
			throw new DataValidationException("Enter  opition 4");

		}  if (question.getAnswer() == null || question.getAnswer().isBlank()) {
			throw new DataValidationException("Enter  Answer");

		}  if (question.getCategory() == null ) {
			throw new DataValidationException("Enter category");
		}  /*if (question.getCategory().getCid()) {
			throw new DataValidationException("Enter Category");
		}*/
		
		
		
		
		
		
		
		if(question.getOption1() == question.getOption2() || question.getOption1() == question.getOption3() || question.getOption1() == question.getOption4()){
			throw new DataValidationException("Options Are Same");
		}
		if(question.getOption2() == question.getOption4() || question.getOption2() == question.getOption3()){
			throw new DataValidationException("Options Are Same");
		}
		if( question.getOption3() == question.getOption4()){
			throw new DataValidationException("Options Are Same");
		}
		
		
		
		
		/*if(question.getAnswer() == question.getOption1() ||
				question.getAnswer() == question.getOption2() ||
				question.getAnswer() == question.getOption3() ||
				question.getAnswer() == question.getOption4()) {
			questionRepository.save(question);
			return new GenericResponse(201, "Created Succesfully!!");
		}*/
		
		
		
		
			questionRepository.save(question);
			return new GenericResponse(201, "Created Succesfully!!");
		

	}

	@Override
	public GenericResponse updateQuestion(Question question, int questionId) {
		question.setQuesId(questionId);
		this.questionRepository.save(question );
		return new GenericResponse(202, "Updated Succesfully!!");
	}

	@Override
	public Page<Question> getQuestions(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Question> question;

		question = questionRepository.findAll(pageable);
		return question;
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}



	

	/*
	 * @Override public Set<Question> getQuestionOfQuiz(Quiz quiz) { return
	 * this.questionRepository.findByQuiz(quiz) ; }
	 */

	/*
	 * @Override public Set<Question> getQuestionOfQuiz(Quiz quiz) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
