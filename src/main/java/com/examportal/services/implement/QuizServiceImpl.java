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
import com.examportal.model.exam.Question;
import com.examportal.model.exam.QuestionDto;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;
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

		} else if (quiz.getCategory() == null || quiz.getCategory().getId() == null) {
			throw new DataValidationException("Please Fill the Category");

		}
		quizRepository.save(quiz);
		return new GenericResponse(201, "Created Succesfully");

	}

	@Override
	public GenericResponse updateQuiz(Quiz quiz, long quizId) {
		Optional<Quiz> quizOptional = quizRepository.findById(quizId);

		if (quizOptional.isPresent()) {
			Quiz presentQuiz = quizOptional.get();
			presentQuiz.setTitle(quiz.getTitle());
			presentQuiz.setDescription(quiz.getDescription());
			presentQuiz.setCategory(quiz.getCategory());
			presentQuiz.setQuestions(quiz.getQuestions());

			return new GenericResponse(202, "Updated Succesfully");

		} else {
			return new GenericResponse(404, "ID NOT FOUND");
		}

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
	public GenericResponse changeQuizStatus(Long id, boolean newStatus) {
		Optional<Quiz> quizOptional = quizRepository.findById(id);
		if (quizOptional.isPresent()) {
			Quiz quiz = quizOptional.get();
			quiz.setActive(newStatus);
			quizRepository.save(quiz);
			return new GenericResponse(202, "Quiz Status Updated Succesfully");
		}

		return new GenericResponse(404, "ID NOT FOUND");
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		return quizRepository.findByIsActiveTrue();

	}

	@Override
	public QuizDto convertQuizToQuizDto(Quiz quiz) {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(quiz.getId());
		quizDto.setTitle(quiz.getTitle());
		quizDto.setDescription(quiz.getDescription());

		List<QuestionDto> questionDtos = new ArrayList<>();
		for (Question question : quiz.getQuestions()) {
			QuestionDto questionDto = new QuestionDto(question.getId(), question.getContent(), question.getOption1(),
					question.getOption2(), question.getOption3(), question.getOption4()

			);
			questionDtos.add(questionDto);
		}

		quizDto.setQuestions(questionDtos);

		return quizDto;

	}
}
