package com.kodigo.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.quiz.model.AdminUser;
import com.kodigo.quiz.model.QuestionPool;
import com.kodigo.quiz.pojo.QuestionPoolPojo;
import com.kodigo.quiz.repositories.AdminRepository;
import com.kodigo.quiz.repositories.QuestionPoolRepository;

@Service("questionPoolService")
public class QuestionPoolServiceImpl implements QuestionPoolService {
	
	@Autowired
	private QuestionPoolRepository questionPoolRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	

	@Override
	public List<QuestionPoolPojo> getPoolByAdmin(Long adid) {
		AdminUser adminUser = adminRepository.findByAdid(adid);
		return entityToPojo(questionPoolRepository.findByAdminUser(adminUser));
	}
	
	private List<QuestionPoolPojo> entityToPojo(List<QuestionPool> questionPoolList){
		List<QuestionPoolPojo> pojoList = new ArrayList<>();
		for(QuestionPool pool : questionPoolList) {
			QuestionPoolPojo poolPojo = new QuestionPoolPojo();
			poolPojo.setQpid(pool.getQpid());
			poolPojo.setName(pool.getName());
			pojoList.add(poolPojo);
		}
		return pojoList;

	}
	
	@Override
	public QuestionPool savePool(QuestionPool questionPool, Long adid) {

		questionPool.setAdminUser(adminRepository.getById(adid));

		return questionPoolRepository.save(questionPool);
	}


	@Override
	public Map<String, Boolean> deletePool(QuestionPool questionPool, Long qpid) {
		
		questionPool.setQpid(questionPool.getQpid());
		
		questionPoolRepository.delete(questionPool);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
	@Override
	public QuestionPool updatePool(QuestionPool questionPool) {
		
		QuestionPool currentQuestionPool = questionPoolRepository.findByQpid(questionPool.getQpid());
		
		mergePool(currentQuestionPool, questionPool);
		return questionPoolRepository.save(currentQuestionPool);
	}

	private void mergePool(QuestionPool currentQuestionPool, QuestionPool questionPool) {
		currentQuestionPool.setName(questionPool.getName());
	}

	@Override
	public QuestionPoolPojo getPool(Long adid) {
		QuestionPool pool = questionPoolRepository.getById(adid);
		
		QuestionPoolPojo pojo = new QuestionPoolPojo();
		pojo.setName(pool.getName());
		pojo.setQpid(pool.getQpid());
		
		return pojo;
	}
	
}
