package com.kodigo.quiz.service;

import java.util.List;
import java.util.Map;

import com.kodigo.quiz.model.QuestionGroup;
import com.kodigo.quiz.pojo.QuestionGroupPojo;

public interface QuestionGroupService {

	List<QuestionGroupPojo> getGroupByPool(Long qpid);

	QuestionGroup saveGroup(QuestionGroup questionGroup, Long qpid);

	QuestionGroup updateGroup(QuestionGroup questionGroup);

	Map<String, Boolean> deleteGroup (QuestionGroup questionGroup, Long qgid);
	
	QuestionGroupPojo getGroup(Long qgid);

}
