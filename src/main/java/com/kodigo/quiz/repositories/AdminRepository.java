package com.kodigo.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodigo.quiz.model.AdminUser;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<AdminUser, Long> {

	
	AdminUser findByAdid (Long adid);
	
	AdminUser findByPin (String pin);

}
