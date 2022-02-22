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

import com.kodigo.quiz.model.QuestionGroup;
import com.kodigo.quiz.pojo.QuestionGroupPojo;
import com.kodigo.quiz.service.QuestionGroupService;

@RestController
public class QuestionGroupController {
	
	@Autowired
	private QuestionGroupService questionGroupService;
	
	@PostMapping("/add/group/{qpid}")
	public String saveGroup(@PathVariable Long qpid, @RequestBody QuestionGroup questionGroup) {
		String returnString = "";
		try {
			questionGroupService.saveGroup(questionGroup, qpid);
			returnString = "Group added successfully";
		} catch (Exception e) {
			returnString = "Error: Group could not be added";
		}
		return returnString;
	}
	
	@GetMapping("/group/{qpid}")
	public List<QuestionGroupPojo> getGroup(@PathVariable Long qpid) {
		
		return questionGroupService.getGroupByPool(qpid);
	}

	@DeleteMapping("/delete/group/{qgid}")
	public String deleteGroup(@PathVariable Long qgid, QuestionGroup questionGroup) {
		String returnString = "";
		try {
			questionGroupService.deleteGroup(questionGroup, qgid);
			returnString = "Question group deleted successfully";
		}
		catch(Exception e) {
			returnString = "Error: Question group could not be deleted";
		}
		return returnString;
	}
	
	
	@PutMapping("/edit/group/{qgid}")
	public String editGroup(@PathVariable Long qgid, @RequestBody QuestionGroup questionGroup) {
		String returnString = "";
		try {
			questionGroup.setQgid(qgid);
			questionGroupService.updateGroup(questionGroup);
			returnString = "Question group updated successfully";
		}
		catch(Exception e) {
			returnString = "Error: Question group could not be updated";
		}
		return returnString;
	}
	
	@GetMapping("/findgroup/{qgid}")
	public QuestionGroupPojo getOne (@PathVariable Long qgid) {
		return questionGroupService.getGroup(qgid);
		
	}

}
