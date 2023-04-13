package com.kodigo.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.quiz.model.Question;
import com.kodigo.quiz.model.QuestionGroup;
import com.kodigo.quiz.pojo.QuestionPojo;
import com.kodigo.quiz.repositories.QuestionGroupRepository;
import com.kodigo.quiz.repositories.QuestionRepository;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuestionGroupRepository questionGroupRepository;



	@Override
	public Question saveQuestion(Question question, Long qgid) {
		question.setQuestionGroup(questionGroupRepository.getById(qgid));
		
		return questionRepository.save(question);
	}
	
	@Override
	public List<QuestionPojo> getByGroup(Long qgid) {
		QuestionGroup QuestionGroup = questionGroupRepository.findByQgid(qgid);
		return entityToPojo(questionRepository.findByQuestionGroup(QuestionGroup));
	}
	
	private List<QuestionPojo> entityToPojo(List<Question> questionList) {
		List<QuestionPojo> pojoList = new ArrayList<>();
		for (Question question : questionList) {
			QuestionPojo questionPojo = new QuestionPojo();
			questionPojo.setQid(question.getQid());
			questionPojo.setQuestion(question.getQuestion());
			questionPojo.setAnswer1(question.getAnswer1());
			questionPojo.setAnswer2(question.getAnswer2());
			questionPojo.setAnswer3(question.getAnswer3());
			questionPojo.setAnswer4(question.getAnswer4());
			questionPojo.setCorrect(question.getCorrect());
			pojoList.add(questionPojo);
		}
		return pojoList;
	}

	@Override
	public Map<String, Boolean> deleteQuestion(Question question, Long qid) {
		
		question.setQid(question.getQid());
		
		questionRepository.delete(question);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
	@Override
	public Question updateQuestion(Question question) {
		
		Question currentQuestion = questionRepository.findByQid(question.getQid());
		
		mergeQuestion(currentQuestion, question);
		return questionRepository.save(currentQuestion);
	}

	private void mergeQuestion(Question currentQuestion, Question question) {
		currentQuestion.setQuestion(question.getQuestion());
		currentQuestion.setAnswer1(question.getAnswer1());
		currentQuestion.setAnswer2(question.getAnswer2());
		currentQuestion.setAnswer3(question.getAnswer3());
		currentQuestion.setAnswer4(question.getAnswer4());
		currentQuestion.setCorrect(question.getCorrect());
	}

	@Override
	public QuestionPojo getQuestion(Long qgid) {
		Question question = questionRepository.getById(qgid);
		
		QuestionPojo pojo = new QuestionPojo();
		pojo.setQid(question.getQid());
		pojo.setQuestion(question.getQuestion());
		pojo.setAnswer1(question.getAnswer1());
		pojo.setAnswer2(question.getAnswer2());
		pojo.setAnswer3(question.getAnswer3());
		pojo.setAnswer4(question.getAnswer4());
		pojo.setCorrect(question.getCorrect());
		
		return pojo;
	}
	
	
	
//	@Override
//	public List<PlayerAnswerPojo> getQuestionAnswer(Long qgid) {
//		QuestionGroup QuestionGroup = questionGroupRepository.findByQgid(qgid);
//		return pojoEntity(questionRepository.getQuestionAnswer(qgid));
//		
//		List<PlayerAnswerPojo> list = new ArrayList<>();
//	}
//	
//	private List<PlayerAnswerPojo> pojoEntity(List<Question> questionList) {
//		List<PlayerAnswerPojo> pojoList = new ArrayList<>();
//		for (Question question : questionList) {
//			QuestionPojo questionPojo = new QuestionPojo();
//			questionPojo.setQid(question.getQid());
//			questionPojo.setQuestion(question.getQuestion());
//			questionPojo.setAnswer1(question.getAnswer1());
//			questionPojo.setAnswer2(question.getAnswer2());
//			questionPojo.setAnswer3(question.getAnswer3());
//			questionPojo.setAnswer4(question.getAnswer4());
//			questionPojo.setCorrect(question.getCorrect());
//			pojoList.add(questionPojo);
//		}
//		return pojoList;
//	}
	@Override
	public List<QuestionPojo> getQuestionAnswerTest(Long qgid) {
		QuestionGroup QuestionGroup = questionGroupRepository.findByQgid(qgid);
		return pojoEntity(questionRepository.findByQuestionGroup(QuestionGroup));
	}
	
	private List<QuestionPojo> pojoEntity(List<Question> questionList) {
		List<QuestionPojo> pojoList = new ArrayList<>();
		for (Question question : questionList) {
			QuestionPojo questionPojo = new QuestionPojo();
			questionPojo.setQid(question.getQid());
			questionPojo.setQuestion(question.getQuestion());
			questionPojo.setCorrect(question.getCorrect());
			pojoList.add(questionPojo);
		}
		return pojoList;
	}

}
