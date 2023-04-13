package com.kodigo.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodigo.quiz.model.QuestionGroup;
import com.kodigo.quiz.model.QuestionPool;

@Repository("questionGroupRepository")
public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long> {
	
	List<QuestionGroup> findByQuestionPool (QuestionPool questionPool);
	
	QuestionGroup findByQgid (Long qgid);

}
