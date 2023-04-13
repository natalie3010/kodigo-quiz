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

import com.kodigo.quiz.model.QuestionPool;
import com.kodigo.quiz.pojo.QuestionPoolPojo;
import com.kodigo.quiz.service.QuestionPoolService;


@RestController
public class QuestionPoolController {
	
	@Autowired
	private QuestionPoolService questionPoolService;
	
	@PostMapping("/add/pool/{adid}")
	public String savePool(@PathVariable Long adid, @RequestBody QuestionPool questionPool) {
		String returnString = "";
		try {
			questionPoolService.savePool(questionPool, adid);
			returnString = "Pool added successfully";
		}
		catch(Exception e) {
			returnString = "Error: Pool could not be added";
		}
		return returnString;
	}
	
	@GetMapping("/users/{adid}")
	public List<QuestionPoolPojo> getPool(@PathVariable Long adid) {

		return questionPoolService.getPoolByAdmin(adid);
	}

	@DeleteMapping("/delete/pool/{qpid}")
	public String deletePool(@PathVariable Long qpid, QuestionPool questionPool) {
		String returnString = "";
		try {
			questionPoolService.deletePool(questionPool, qpid);
			returnString = "Pool deleted successfully";
		}
		catch(Exception e) {
			returnString = "Error: Pool could not be deleted";
		}
		return returnString;
	}
	
	
	@PutMapping("/edit/pool/{qpid}")
	public String editPool(@PathVariable Long qpid, @RequestBody QuestionPool questionPool) {
		String returnString = "";
		try {
			questionPool.setQpid(qpid);
			questionPoolService.updatePool(questionPool);
			returnString = "Pool updated successfully";
		}
		catch(Exception e) {
			returnString = "Error: Pool could not be updated";
		}
		return returnString;
	}
	
	@GetMapping("/findpool/{qpid}")
	public QuestionPoolPojo getOne (@PathVariable Long qpid) {
		return questionPoolService.getPool(qpid);
		
	}

}