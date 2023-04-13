package com.kodigo.quiz.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kodigo.quiz.exceptions.UserAlreadyExistsException;
import com.kodigo.quiz.model.PlayerUser;
import com.kodigo.quiz.pojo.PlayerAnswerPojo;
import com.kodigo.quiz.pojo.PlayerScore;
import com.kodigo.quiz.pojo.PlayerUserPojo;

@Component
public interface PlayerService {
    
    String savePlayer(PlayerUser playerUser, Long qid, Long qgid) throws UserAlreadyExistsException;
    
    List<PlayerScore> getNameScore (Long qgid);
    
//  PlayerUserPojo getLeader (Long qgid);
    
    List<PlayerUserPojo> getPlayerByName (String playername);
    
    List<PlayerUserPojo> getPlayerById(Long qid);

//  List<PlayerAnswerPojo> getPlayerAnswer(Long qgid, String playername, Long qid);
    
    List<PlayerAnswerPojo> getPlayerAnswer (Long qgid, String playername);
    
    void deletePlayerAnswer(Long qgid);
    
    
    
}