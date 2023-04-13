package com.kodigo.quiz.pojo;

import org.springframework.stereotype.Component;

@Component
public class PlayerScore {
	
	private Long id;
	private String playername;
	private String score;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	

}
