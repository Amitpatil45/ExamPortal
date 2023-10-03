package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Question;
import com.examportal.services.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	// add
	@PostMapping("/")
	public ResponseEntity<GenericResponse> add(@RequestBody Question question) throws Exception {
		GenericResponse response = questionService.addQuestion(question);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public Page<Question> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		return questionService.getQuestions(page, size);
	}

	// update
	@PutMapping("/")
	public ResponseEntity<GenericResponse> update(@RequestBody Question question) {
		GenericResponse response = questionService.updateQuestion(question);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	// get
	/*
	 * @GetMapping("/quiz/{qid}") public ResponseEntity<?>
	 * questionsofquiz(@PathVariable ("qid") Long qid){ Quiz quiz =
	 * quizService.getQuiz(qid); Set<Question> questions = quiz.getQuestions(); List
	 * list = new ArrayList(questions); if(list.size()>
	 * Integer.parseInt(quiz.getNoOfQuestions())) { list = list.subList(0,
	 * Integer.parseInt(quiz.getNoOfQuestions()+1)); } Collections.shuffle(list);
	 * 
	 * return ResponseEntity.ok(list);
	 * 
	 * }
	 */

	@GetMapping("/{qid}")

	public Question question(@PathVariable("qid") Long qid) {
		return questionService.getQuestion(qid);

	}

}
