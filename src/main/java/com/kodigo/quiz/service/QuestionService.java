package com.kodigo.quiz.service;

import java.util.List;
import java.util.Map;

import com.kodigo.quiz.model.Question;
import com.kodigo.quiz.pojo.QuestionPojo;

public interface QuestionService {
	
	Question saveQuestion(Question question, Long qgid);
	
	List<QuestionPojo> getByGroup(Long qgid);
	
	Map<String, Boolean> deleteQuestion (Question question, Long qid);
	
	Question updateQuestion (Question question);
	
	QuestionPojo getQuestion (Long qgid);
	
	List<QuestionPojo> getQuestionAnswerTest(Long qgid);
	
}
