package com.examportal.services.implement;

import java.util.Optional;

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

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public GenericResponse addQuestion(Question question) throws Exception {

		if (question.getContent() == null || question.getContent().isBlank()) {
			throw new DataValidationException("Enter  Question !!");

		}

		if (question.getOption1() == null || question.getOption1().isBlank() || question.getOption2() == null
				|| question.getOption2().isBlank() || question.getOption3() == null || question.getOption3().isBlank()
				|| question.getOption4() == null || question.getOption4().isBlank()) {
			throw new DataValidationException("Enter  Option ");

		}

		/*if (question.getAnswer() == null || question.getAnswer().isBlank()) {
			throw new DataValidationException("Enter  Answer !!");

		}*/

		if (question.getCategory() == null) {
			throw new DataValidationException("Enter category");
		}

	
		
		
		

		if (!questionUnique(question)) {
			throw new DataValidationException("Question is Already present");

		}
		if (!optionunique(question)) {
			throw new DataValidationException("options are same!!");
		}
		/*if (!answerunique(question)) {
			throw new DataValidationException("Answer is not present in option !!");
		}*/

		/*
		 * if (question.getCategory().getCid()) { throw new
		 * DataValidationException("Enter Category"); }
		 */

		questionRepository.save(question);
		return new GenericResponse(201, "Created Succesfully!!");

	}

	private Boolean questionUnique(Question question1) {

		Optional<Question> questionOptional = questionRepository.findByContent(question1.getContent());
		System.out.println(questionOptional);
		if (questionOptional.isPresent()) {
			return false;
		}

		return true;

	}

	private Boolean optionunique(Question question) {
		return !(question.getOption1().equals(question.getOption2())
				|| question.getOption1().equals(question.getOption3())
				|| question.getOption1().equals(question.getOption4())
				|| question.getOption2().equals(question.getOption3())
				|| question.getOption2().equals(question.getOption4())
				|| question.getOption3().equals(question.getOption4()));

	}

	/*private Boolean answerunique(Question question) {
		return question.getAnswer().equals(question.getOption1()) || question.getAnswer().equals(question.getOption2())
				|| question.getAnswer().equals(question.getOption3())
				|| question.getAnswer().equals(question.getOption4());

	}*/

	@Override
	public GenericResponse updateQuestion(Question question, int questionId) {
		question.setQuesId(questionId);
		this.questionRepository.save(question);
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
