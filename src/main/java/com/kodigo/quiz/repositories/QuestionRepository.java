package com.kodigo.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodigo.quiz.model.PlayerUser;
import com.kodigo.quiz.model.Question;
import com.kodigo.quiz.model.QuestionGroup;


@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByQuestionGroup(QuestionGroup questionGroup);
	
	Question findByQuestionGroup (Long qgid);
	
	List<Question> findByPlayerUser (PlayerUser playerUser);
	
	Question findByQid (Long qid);
	
//	@Query("SELECT q.question, q.correct, q.qid "
//			+ "FROM Question q "
//			+ "WHERE qgid=?1")
//	List<String> getQuestionAnswer (Long qgid);

}
