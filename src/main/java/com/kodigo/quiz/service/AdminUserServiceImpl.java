package com.kodigo.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.quiz.exceptions.DataUnavailableException;
import com.kodigo.quiz.exceptions.UserAlreadyExistsException;
import com.kodigo.quiz.model.AdminUser;
import com.kodigo.quiz.pojo.AdminPojo;
import com.kodigo.quiz.repositories.AdminRepository;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public String saveUser(AdminUser adminUser) throws UserAlreadyExistsException {
		
		try {
	
		if (adminRepository.findByPin(adminUser.getPin()) != null) {
			throw new UserAlreadyExistsException("The pin " + adminUser.getPin() + " is already taken. Please choose another.");
		}
		else {
			adminRepository.save(adminUser);
		}
		}
		catch(Exception e){
			throw e;
		}
		
		return "User added successfully.";
	}
	
	@Override
	public List<AdminPojo> getAdminByPin(String pin) throws DataUnavailableException {
		
		AdminUser adminUser = adminRepository.findByPin(pin);
		List<AdminPojo> adminPojo = new ArrayList<>();
		
		AdminPojo admin = new AdminPojo();
		
		admin.setAdid(adminUser.getAdid());
		admin.setName(adminUser.getName());
		
		adminPojo.add(admin);
	
		
			return adminPojo;
		
	}
}
