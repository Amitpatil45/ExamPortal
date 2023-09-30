package com.examportal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.services.QuestionService;
import com.examportal.services.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) throws Exception{
		return ResponseEntity.ok(questionService.addQuestion(question));
		
	}
	
	/*@GetMapping("/")
	Page<Question> getAllQuestion(
			@RequestParam (defaultValue = o) int page,
			@RequestParam (defaultValue = 20)){
		return null;
		
	}*/
	
	//update
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question){
		return ResponseEntity.ok(questionService.updateQuestion(question));
	}
	
	//get
	/*@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> questionsofquiz(@PathVariable ("qid") Long qid){
		Quiz quiz = quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if(list.size()> Integer.parseInt(quiz.getNoOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestions()+1));
		}
		Collections.shuffle(list);
		
		return ResponseEntity.ok(list);
		
	}*/
	
	@GetMapping("/{qid}")
	
	public Question question(@PathVariable("qid") Long qid) {
		return questionService.getQuestion(qid);
		
	}
	
	
}
