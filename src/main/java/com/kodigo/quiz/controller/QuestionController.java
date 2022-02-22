package com.kodigo.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodigo.quiz.model.Question;
import com.kodigo.quiz.pojo.QuestionPojo;
import com.kodigo.quiz.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	//Controller to save new question to DB
	@PostMapping("/add/question/{qgid}")
	public String saveQuestion(@PathVariable Long qgid, @RequestBody Question question) {
		String returnString = "";
		try {
			questionService.saveQuestion(question, qgid);
			returnString = "Question added successfully";
		}
		catch(Exception e) {
			returnString = "Error: Question could not be added";
		}
		return returnString;
	}

	
	//Controller to pull the questions from the DB based on the question group
	@GetMapping("/groups/{qgid}")
	public List<QuestionPojo> getQuestions(@PathVariable Long qgid) {

		return questionService.getByGroup(qgid);
	}
	
	
	//Controller to delete question from DB
	@DeleteMapping("/delete/question/{qid}")
	public String deleteQuestion(@PathVariable Long qid, Question question) {
		String returnString = "";
		try {
			questionService.deleteQuestion(question, qid);
			returnString = "Question deleted successfully";
		}
		catch(Exception e) {
			returnString = "Error: Question could not be deleted";
		}
		return returnString;
	}
	

	@PutMapping("/edit/question/{qid}")
	public String editQuestion(@PathVariable Long qid, @RequestBody Question question) {
		String returnString = "";
		try {
			question.setQid(qid);
			questionService.updateQuestion(question);
			returnString = "Question updated successfully";
		}
		catch(Exception e) {
			returnString = "Error: Question could not be updated";
		}
		return returnString;
	}
	
	@GetMapping("/findquestion/{qid}")
	public QuestionPojo getQuestion(@PathVariable Long qid) {
		return questionService.getQuestion(qid);
		
	}
	
	@GetMapping("/player/question/{qgid}")
	List<QuestionPojo> getQuestionAnswerTest(@PathVariable Long qgid){
		return questionService.getQuestionAnswerTest(qgid);
	}

}