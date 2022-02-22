package com.kodigo.quiz.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kodigo.quiz.exceptions.DataUnavailableException;
import com.kodigo.quiz.exceptions.UserAlreadyExistsException;
import com.kodigo.quiz.model.AdminUser;
import com.kodigo.quiz.pojo.AdminPojo;

@Component
public interface AdminUserService {
	
	String saveUser(AdminUser adminUser) throws UserAlreadyExistsException;
	
	List<AdminPojo> getAdminByPin (String pin) throws DataUnavailableException;
	
//	Long getAdminByAdid (Long adid) throws DataUnavailableException;

}
