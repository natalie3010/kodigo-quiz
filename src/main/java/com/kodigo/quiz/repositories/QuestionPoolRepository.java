package com.kodigo.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodigo.quiz.model.AdminUser;
import com.kodigo.quiz.model.QuestionPool;

@Repository("questionPoolRepository")
public interface QuestionPoolRepository extends JpaRepository<QuestionPool, Long>  {
	
	List<QuestionPool> findByAdminUser (AdminUser adminUser);
	
	QuestionPool findByQpid (Long qpid);
	

}
