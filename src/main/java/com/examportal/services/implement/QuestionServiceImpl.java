package com.examportal.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examportal.model.exam.Question;
import com.examportal.repo.QuestionRepository;
import com.examportal.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) throws Exception {
		if (question.getContent() == null) {
			throw new Exception("Please Enter Question");

		} else if (question.getOption1() == null) {
			throw new Exception("Enter Option 1");

		} else if (question.getOption2() == null) {
			throw new Exception("Enter Option 2");

		} else if (question.getOption3() == null) {
			throw new Exception("Enter Option 3");

		} else if (question.getOption4() == null) {
			throw new Exception("Enter Option 4");

		} else if (question.getAnswer() == null) {
			throw new Exception("Enter Answer");

		}

		return questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Page<Question> getQuestions(int page , int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Question> question;

		question = questionRepository.findAll(pageable);
		return question ;
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	/*
	 * @Override public Set<Question> getQuestionOfQuiz(Quiz quiz) { return
	 * this.questionRepository.findByQuiz(quiz) ; }
	 */

	/*@Override
	public Set<Question> getQuestionOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
