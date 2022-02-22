package com.kodigo.quiz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long qid;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "qgid", nullable = false)
	private QuestionGroup questionGroup;
	
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correct;
	
	//OneToMany relationship parent - mapping question to playerUser
		@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL)
		private List<PlayerUser> playerUser;
	
	public Question() {}

	public Question(QuestionGroup questionGroup, String question, String answer1, String answer2, String answer3,
			String answer4, String correct) {
		super();
		this.questionGroup = questionGroup;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correct = correct;
	}

	public Question(String question, String answer1, String answer2, String answer3, String answer4, String correct) {
		super();
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correct = correct;
	}

	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

}
