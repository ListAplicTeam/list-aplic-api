package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.util.AnswerCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findByApplicationId(UUID applicationId);

    List<AnswerCount> findAnswerCountsByClassroomId(UUID classroomId);
}
