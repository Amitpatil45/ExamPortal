package com.examportal.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.exception.DataValidationException;
import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.CorrectOption;
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

		if (question.getOption1() != null || question.getOption1().isBlank()) {
			question.setOption1(CorrectOption.A);
		}

		if (question.getOption2() != null || question.getOption2().isBlank()) {
			question.setOption2(CorrectOption.B);
		}
		if (question.getOption3() != null || question.getOption3().isBlank()) {
			question.setOption3(CorrectOption.C);
		}

		if (question.getOption4() != null || question.getOption4().isBlank()) {
			question.setOption4(CorrectOption.D);
		}

		if (question.getOption1() == null || question.getOption2() == null || question.getOption3() == null
				|| question.getOption4() == null) {
			throw new DataValidationException("Enter  Option ");

		}

		if (question.getCategory() == null ) {
			throw new DataValidationException("Enter category");
		}
		
		
		trimquestion(question);
	
		if (!questionUnique(question)) {
			throw new DataValidationException("Question is Already present");

		}
		if (!optionunique(question)) {
			throw new DataValidationException("options are same!!");
		}

		questionRepository.save(question);
		return new GenericResponse(201, "Created Succesfully!!");

	}

	private void trimquestion(Question question) {
		if (question.getContent() != null) {
			question.setContent(question.getContent().trim());
		}
		if (question.getOption1() != null) {
			question.setOption1(question.getOption1().trim());
		}
		if (question.getOption2() != null) {
			question.setOption2(question.getOption2().trim());
		}
		if (question.getOption3() != null) {
			question.setOption3(question.getOption3().trim());
		}
		if (question.getOption4() != null) {
			question.setOption4(question.getOption4().trim());
		}

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

	@Override
	public GenericResponse updateQuestion(Question question, int questionId) {
		question.setId((long) questionId);
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
