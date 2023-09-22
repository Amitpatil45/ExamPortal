package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.exam.Quiz;
import com.examportal.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add
	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.addQuiz(quiz));

	}

	// update
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.updateQuiz(quiz));

	}

	// get
	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(quizService.getQuizzes());

	}

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return quizService.getQuiz(qid);

	}

	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
		quizService.deleteQuiz(qid);
	}

}
