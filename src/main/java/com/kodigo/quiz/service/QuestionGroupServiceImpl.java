package com.kodigo.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.quiz.model.QuestionGroup;
import com.kodigo.quiz.model.QuestionPool;
import com.kodigo.quiz.pojo.QuestionGroupPojo;
import com.kodigo.quiz.repositories.QuestionGroupRepository;
import com.kodigo.quiz.repositories.QuestionPoolRepository;


@Service("questionGroupService")
public class QuestionGroupServiceImpl implements QuestionGroupService {
	
	@Autowired
	private QuestionGroupRepository questionGroupRepository;
	
	@Autowired
	private QuestionPoolRepository questionPoolRepository;


	@Override
	public List<QuestionGroupPojo> getGroupByPool(Long qpid) {
		QuestionPool questionPool = questionPoolRepository.findByQpid(qpid);
		return EntityToPojo(questionGroupRepository.findByQuestionPool(questionPool));
	}
	
	private List<QuestionGroupPojo> EntityToPojo(List<QuestionGroup> questionGroupList){
		List<QuestionGroupPojo> groupPojoList = new ArrayList<>();
		for(QuestionGroup group : questionGroupList) {
			QuestionGroupPojo groupPojo = new QuestionGroupPojo();
			groupPojo.setQgid(group.getQgid());
			groupPojo.setName(group.getName());
			groupPojoList.add(groupPojo);
		}
		return groupPojoList;	
	}

	@Override
	public QuestionGroup saveGroup(QuestionGroup questionGroup, Long qpid) {
		questionGroup.setQuestionPool(questionPoolRepository.getById(qpid));

		
		//QuestionGroup group = new QuestionGroup();
		//group.setName(groupName);
		//group.setQuestionPool(new QuestionPool(qpid));
		return questionGroupRepository.save(questionGroup);
	}


	@Override
	public Map<String, Boolean> deleteGroup(QuestionGroup questionGroup, Long qqid) {

		questionGroup.setQgid(questionGroup.getQgid());
		
		questionGroupRepository.delete(questionGroup);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;	
	}
	
	
	@Override
	public QuestionGroup updateGroup(QuestionGroup questionGroup) {
		
		QuestionGroup currentQuestionGroup = questionGroupRepository.findByQgid(questionGroup.getQgid());
		
		mergeGroup(currentQuestionGroup, questionGroup);
		return questionGroupRepository.save(currentQuestionGroup);
	}

	private void mergeGroup(QuestionGroup currentQuestionGroup, QuestionGroup questionGroup) {
		currentQuestionGroup.setName(questionGroup.getName());
	}

	@Override
	public QuestionGroupPojo getGroup(Long qgid) {
		QuestionGroup group = questionGroupRepository.getById(qgid);
		
		QuestionGroupPojo pojo = new QuestionGroupPojo();
		pojo.setName(group.getName());
		pojo.setQgid(group.getQgid());
		
		return pojo;
	}
}
 

