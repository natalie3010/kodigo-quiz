package com.kodigo.quiz.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kodigo.quiz.model.PlayerUser;
import com.kodigo.quiz.model.Question;


@Repository("playerRepository")
public interface PlayerRepository extends JpaRepository<PlayerUser, Long> {

    List<PlayerUser> findByPlayername(String playername);
    
    List<PlayerUser> findByQgid (Long qgid);
    
    @Query("select m.playername, sum(m.score) from PlayerUser m where qgid = ?1 group by m.playername order by sum(m.score) desc")
    List<String> getScore(Long qgid);
    
    @Query("select m.id, m.playername, sum(m.score) from PlayerUser m where qgid = ?1 group by m.playername")
    List<PlayerUser> getLeader(Long qgid);
    
    List<PlayerUser> findByplayername(String playerUser);
    
    List<PlayerUser> findByQuestion (Question question);
    
//  @Query("SELECT q.question, q.correct, p.playername, p.answer "
//          + "FROM Question q JOIN PlayerUser p "
//          + "WHERE p.qgid=?1 and p.playername=?2 "
//          + "AND q.qid in (SELECT q.qid "
//          + "FROM Question q "
//          + "WHERE q.qid = p.qid)")
//  List<PlayerUser> getLeaderboard(Long qgid, String playername);
    
    @Query("SELECT p.playername, p.answer "
            + "FROM PlayerUser p "
            + "WHERE p.qgid=?1 and p.playername=?2")
    List<String> getPlayerAnswer (Long qgid, String playername);
    
    @Transactional
    @Modifying
    @Query("delete from PlayerUser p where p.qgid=?1")
    void deleteByQgid(Long qgid);
        
    
}