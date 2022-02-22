package com.kodigo.quiz.pojo;

import org.springframework.stereotype.Component;

@Component
public class PlayerAnswerPojo {
	
	private Long id;
	
	private String question;
	private String correct;
	private String playername;
	private String answer;
	private Long qgid;

	
	public Long getQgid() {
		return qgid;
	}
	public void setQgid(Long qgid) {
		this.qgid = qgid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	
	

}
