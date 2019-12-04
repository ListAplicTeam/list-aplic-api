package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.QuestionCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionCountJpaRepository extends JpaRepository<QuestionCount, UUID> {

    @Query(
            value = "SELECT q FROM QuestionCount q " +
                    "WHERE q.question IN (:questions) AND q.instructor = :instructor"
    )
    List<QuestionCount> findAllByQuestionsAndInstructor(List<UUID> questions, String instructor);

    Optional<QuestionCount> findByQuestionAndInstructor(UUID question, String instructor);

    List<QuestionCount> findAllByInstructor(String instructor);

	@Transactional
	@Modifying
	@Query(
			value = "UPDATE QuestionCount set counter = :counter WHERE question = :question"
	)
	void updateCounter(UUID question, Integer counter);
}
