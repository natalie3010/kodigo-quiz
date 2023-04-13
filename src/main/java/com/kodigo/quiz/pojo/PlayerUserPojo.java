package com.kodigo.quiz.pojo;

public class PlayerUserPojo {

	private Long id;

	private Long qgid;
	private String playername;
	private String answer;
	private int score;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQgid() {
		return qgid;
	}
	public void setQgid(Long qgid) {
		this.qgid = qgid;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
//	public Long getQid() {
//		return qid;
//	}
//	public void setQid(Long qid) {
//		this.qid = qid;
//	}
	
	

}
