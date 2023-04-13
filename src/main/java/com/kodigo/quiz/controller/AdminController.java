package com.kodigo.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodigo.quiz.model.AdminUser;
import com.kodigo.quiz.pojo.AdminPojo;
import com.kodigo.quiz.service.AdminUserService;



@RestController
public class AdminController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	//Controller to log in, getting Admin from DB
	@GetMapping("admin/login/{adminPin}")
	public List<AdminPojo> logIn(@PathVariable String adminPin) {
		return adminUserService.getAdminByPin(adminPin);
			
	}
	//Controller to register and save to DB
	//Controller to register and save to DB
	@PostMapping("admin/register")
	public String saveAdmin(@RequestBody AdminUser adminUser) {
		String returnString = "";
		try {
		adminUserService.saveUser(adminUser);
		returnString = "User added successfully";
		}
		catch(Exception e) {
			returnString = "Error: User could not be added";
		}
		return returnString;
	}
}
	
