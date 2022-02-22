package com.kodigo.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodigo.quiz.model.PlayerUser;
import com.kodigo.quiz.pojo.PlayerAnswerPojo;
import com.kodigo.quiz.pojo.PlayerScore;
import com.kodigo.quiz.pojo.PlayerUserPojo;
import com.kodigo.quiz.service.PlayerService;



@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	

//	//Controller to save player to DB
//	@PostMapping("player/register")
//	public String savePlayer(@RequestBody PlayerUser playerUser) {
//		String returnString = "";
//		try {
//		playerService.saveUser(playerUser);
//		returnString = "Player added successfully";
//		}
//		catch(Exception e) {
//			returnString = "Error: Player could not be added";
//		}
//		return returnString;
//	}
	
	@GetMapping("/player/{qgid}")
	List<PlayerScore> getTally(@PathVariable Long qgid) {
		return playerService.getNameScore(qgid);
	}
	
	@GetMapping("/player/answer/{qgid}/{playername}")
	List<PlayerAnswerPojo> getAnswer(@PathVariable Long qgid, @PathVariable String playername) {
		return playerService.getPlayerAnswer(qgid, playername);
	}
	
//	@GetMapping("/player/{qgid}")
//	public List<PlayerUserPojo> getLeader(@PathVariable Long qgid) {
//		return null;
//	}
//	
	
	//Controller to save player to DB
	@PostMapping("player/register/{qid}/{qgid}")
	public String savePlayer(@PathVariable Long qid, @PathVariable Long qgid, @RequestBody PlayerUser playerUser) {
		String returnString = "";
		try {
			playerService.savePlayer(playerUser, qid, qgid);
			returnString = "Player added successfully";
		}
		catch(Exception e) {
			returnString = "Error: Player could not be added";
		}
		return returnString;
	}
	
	
	@GetMapping("/player/get/{playername}")
	public List<PlayerUserPojo> getPlayerByName(@PathVariable String playername) {

		return playerService.getPlayerByName(playername);
	}
	 
	@GetMapping("/player/fetch/{qid}")
	public List<PlayerUserPojo> getPlayerById(@PathVariable Long qid) {

		return playerService.getPlayerById(qid);
	}
	
	@DeleteMapping("/delete/player/{qgid}")
    public String deletePlayer(@PathVariable Long qgid) {
        String returnString = "";
        try {
            playerService.deletePlayerAnswer(qgid);
            returnString = "Player records deleted successfully";
        }
        catch(Exception e) {
            returnString = "Error: Player records could not be deleted" + e;
        }
        return returnString;
    }
	
}
	

