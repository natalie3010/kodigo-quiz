package com.kodigo.quiz.service;

import java.util.List;
import java.util.Map;

import com.kodigo.quiz.model.QuestionPool;
import com.kodigo.quiz.pojo.QuestionPoolPojo;

public interface QuestionPoolService {
	
	List<QuestionPoolPojo> getPoolByAdmin (Long adid);
	
	QuestionPool savePool (QuestionPool questionPool, Long adid);
	
	QuestionPool updatePool (QuestionPool questionPool);
	
	Map<String, Boolean> deletePool (QuestionPool questionPool, Long qpid);
	
	QuestionPoolPojo getPool (Long adid);

}
