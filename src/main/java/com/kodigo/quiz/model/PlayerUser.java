package com.kodigo.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class PlayerUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //OneToMany relationship child - fetching questionPool to questionGroup
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "qid", nullable = false)
        private Question question;
    
    
    private Long qgid;  
    private String playername;
    private String answer;
    private int score;
    
    
    public PlayerUser() {}
    
    public PlayerUser(Long qgid) {
        super();
        this.qgid = qgid;
    }



    

    public PlayerUser(Long id, String playername, int score) {
        super();
        this.id = id;
        this.playername = playername;
        this.score = score;
    }




    public PlayerUser(Question question, Long qgid, String playername, String answer, int score) {
        super();
        this.question = question;
        this.qgid = qgid;
        this.playername = playername;
        this.answer = answer;
        this.score = score;
        this.qgid = qgid;
    }


    public PlayerUser(String answer, int score, String playername) {
        super();
        this.answer = answer;
        this.score = score;
        this.playername = playername;
    }


    public Question getQuestion() {
        return question;
    }


    public void setQuestion(Question question) {
        this.question = question;
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

    
    public Long getQgid() {
        return qgid;
    }


    public void setQgid(Long qgid) {
        this.qgid = qgid;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    
}