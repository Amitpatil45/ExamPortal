package com.examportal.controller;

import java.util.List;
import java.util.Map;

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
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;
import com.examportal.services.implement.QuizServiceImpl;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizServiceImpl quizServiceImpl;

	@PostMapping("/")
	public ResponseEntity<GenericResponse> add(@RequestBody Quiz quiz) throws Exception {
		GenericResponse response = quizServiceImpl.addQuiz(quiz);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public Page<Quiz> getAllQuiz(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		return quizServiceImpl.getQuizzes(page, size);
	}

	// update
	@PutMapping("/{quizId}")
	public ResponseEntity<GenericResponse> update(@RequestBody Quiz quiz, @PathVariable("quizId") int quizId) {
		GenericResponse response = quizServiceImpl.updateQuiz(quiz, quizId);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{q1uizid}")
	public Quiz getQuizById(@PathVariable("qid") Long qid) {
		return quizServiceImpl.getQuiz(qid);

	}

	@PutMapping("/status/{QuizId}")
	public ResponseEntity<GenericResponse> updateStatus(@PathVariable("id") long id,
			@RequestBody Map<String, Boolean> requestBody) {
		Boolean newStatus = requestBody.get("isActive");
		if (newStatus != null) {
			GenericResponse response = quizServiceImpl.changeQuizStatus(id, newStatus);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(new GenericResponse(400, "The 'isActive' field is missing "),
					HttpStatus.BAD_REQUEST);
		}

	}
//--------------------------------------------------USER-----------------------------------------
	@GetMapping("/list")
	public ResponseEntity<List<Quiz>> allListOfQuiz() {
		List<Quiz> list = quizServiceImpl.getActiveQuizzes();
		return ResponseEntity.status(HttpStatus.FOUND).body(list);

	}

	
	@GetMapping("/play/{quizId}")
    public ResponseEntity<QuizDto> playQuiz(@PathVariable Long quizId) {
        
        Quiz quiz = quizServiceImpl.getQuiz(quizId);

        if (quiz != null) {
            
            QuizDto quizDto = quizServiceImpl.convertQuizToQuizDto(quiz);
            return new ResponseEntity<>(quizDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
}
