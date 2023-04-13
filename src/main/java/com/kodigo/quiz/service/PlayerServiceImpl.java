package com.kodigo.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodigo.quiz.exceptions.UserAlreadyExistsException;
import com.kodigo.quiz.model.PlayerUser;
import com.kodigo.quiz.model.Question;
import com.kodigo.quiz.pojo.PlayerAnswerPojo;
import com.kodigo.quiz.pojo.PlayerScore;
import com.kodigo.quiz.pojo.PlayerUserPojo;
import com.kodigo.quiz.repositories.PlayerRepository;
import com.kodigo.quiz.repositories.QuestionRepository;


@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
    
    @Autowired private PlayerRepository playerRepository;
    @Autowired private QuestionRepository questionRepository;
    
    @Autowired
    private PlayerScore playerScore;
    
    @Autowired
    private PlayerAnswerPojo playerPojo;


    
    @Override
    public List<PlayerAnswerPojo> getPlayerAnswer (Long qgid, String playername){
        
        List<String> list = playerRepository.getPlayerAnswer(qgid, playername);
        List<PlayerAnswerPojo> answer = new ArrayList<>();

        Long id = 1L;
        for(String s : list) {
        
            playerPojo = new PlayerAnswerPojo();
            playerPojo.setId(id);
            playerPojo.setPlayername(s.substring(0, s.lastIndexOf(",")));
            playerPojo.setAnswer(s.substring(s.lastIndexOf(",")+1, s.length()));
            answer.add(playerPojo);
            id++;
        }
        id = 1L;
        return answer;
    }
    
    

    @Override
    public List<PlayerScore> getNameScore(Long qgid) {

        
        List<String> list = playerRepository.getScore(qgid);
        List<PlayerScore> score = new ArrayList<>();
        
        Long id = 1L;
        for(String s : list) {
        
            playerScore = new PlayerScore();
            playerScore.setId(id);
            playerScore.setPlayername(s.substring(0, s.lastIndexOf(",")));
            playerScore.setScore(s.substring(s.lastIndexOf(",")+1, s.length()));
            score.add(playerScore);
            id++;
//          map.put(s.substring(0, s.lastIndexOf(",")), s.substring(s.lastIndexOf(",")+1, s.length()));
        }
        id = 1L;
        return score;
    }

//  @Override
//  public PlayerUserPojo getLeader(Long qgid) {
//      PlayerUser playerUser = playerRepository.getLeader(qgid);
//      return null;
//  }
    
    @Override
    public String savePlayer(PlayerUser playerUser, Long qid, Long qgid) throws UserAlreadyExistsException {

//      try {
//          if (playerRepository.findByPlayername(playerUser.getPlayername()) != null) {
//              throw new UserAlreadyExistsException("The name " + playerUser.getPlayername() + " is already taken. Please choose another.");
//          }
//          else {
                playerUser.setQuestion(questionRepository.getById(qid));
                playerUser.setQgid(qgid);
                
                playerRepository.save(playerUser);
//          }
//          }
//          catch(Exception e){
//              System.out.println("Exception : " + e.getMessage());
//              throw e;
//          }
            return "User added successfully.";
            }
    
    
    @Override
    public List<PlayerUserPojo> getPlayerById(Long qid) {
        
        Question question = questionRepository.findByQid(qid);
        return entityToPojo1(playerRepository.findByQuestion(question));
    }   
        
    private List<PlayerUserPojo> entityToPojo1 (List<PlayerUser> playerList) {
        List<PlayerUserPojo> pojoList = new ArrayList<>();
        for (PlayerUser player : playerList) {
            PlayerUserPojo playerUserPojo = new PlayerUserPojo();
            playerUserPojo.setId(player.getId());
            playerUserPojo.setQgid(player.getQgid());
            playerUserPojo.setPlayername(player.getPlayername());
            playerUserPojo.setAnswer(player.getAnswer());
            playerUserPojo.setScore(player.getScore());
            pojoList.add(playerUserPojo);
        }
        return pojoList;
    }

    
    
    @Override
    public List<PlayerUserPojo> getPlayerByName (String playername) {
        
        return entityToPojo(playerRepository.findByplayername(playername));
    }

    private List<PlayerUserPojo> entityToPojo (List<PlayerUser> playerList) {
        List<PlayerUserPojo> pojoList = new ArrayList<>();
        for (PlayerUser player : playerList) {
            PlayerUserPojo playerUserPojo = new PlayerUserPojo();
            playerUserPojo.setId(player.getId());
            playerUserPojo.setQgid(player.getQgid());
            playerUserPojo.setPlayername(player.getPlayername());
            playerUserPojo.setAnswer(player.getAnswer());
            playerUserPojo.setScore(player.getScore());
            pojoList.add(playerUserPojo);
        }
        return pojoList;
    }



	@Override
	public void deletePlayerAnswer(Long qgid) {
		playerRepository.deleteByQgid(qgid);
	}



    
}
