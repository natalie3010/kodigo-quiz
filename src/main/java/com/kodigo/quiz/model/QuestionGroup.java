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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poolgroups")
public class QuestionGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long qgid;
	
	private String name;
	
	//OneToMany relationship child - fetching questionPool to questionGroup
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "qpid", nullable = false)
	private QuestionPool questionPool;
	
	//OneToMany relationship parent - mapping QuestionGroup to question
	@JsonIgnore
	@OneToMany(mappedBy = "questionGroup", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	private List<Question> question;

	public QuestionGroup() {};
	
	public QuestionGroup(QuestionPool questionPool) {
		super();
		this.questionPool = questionPool;
	}

	public QuestionGroup(String name, QuestionPool questionPool) {
		super();
		this.name = name;
		this.questionPool = questionPool;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuestionPool getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(QuestionPool questionPool) {
		this.questionPool = questionPool;
	}

	public Long getQgid() {
		return qgid;
	}

	public void setQgid(Long qgid) {
		this.qgid = qgid;
	}

	@Override
	public String toString() {
		return "QuestionGroup [qgip=" + qgid + ", name=" + name + ", questionPool=" + questionPool + ", question="
				+ question + "]";
	}
	
}
