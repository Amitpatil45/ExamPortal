package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exception.GenericResponse;
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
	public ResponseEntity<GenericResponse> add(@RequestBody Quiz quiz) throws Exception {
		GenericResponse response = quizService.addQuiz(quiz);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/")
	public ResponseEntity<GenericResponse> update(@RequestBody Quiz quiz) {
		GenericResponse response = quizService.updateQuiz(quiz);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	// get
	@GetMapping("/")
	public Page<Quiz> getAllQuizes(@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "25") int size){
		return quizService.getQuizzes(page, size);
		
	}
	
	
	/*public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(quizService.getQuizzes(0, 0));

	}*/

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return quizService.getQuiz(qid);

	}

	

}
