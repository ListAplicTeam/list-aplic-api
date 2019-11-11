package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.dto.AnswerCountDTO;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ListApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findByApplicationId(UUID applicationId);

    @Query(value = "SELECT CAST(a.application_id AS VARCHAR(40)), CAST(COUNT(DISTINCT a.user_id) AS INTEGER) FROM answer a " +
                    "INNER JOIN application p ON p.id = a.application_id " +
                    "WHERE p.classroom_id = :classroomId " +
                    //"WHERE a.application_id IN (SELECT p.id FROM application p WHERE p.classroom_id = :classroomId) " +
                    "GROUP BY a.application_id", nativeQuery = true)
    List<AnswerCountDTO> findAnswerCountsByClassroomId(UUID classroomId);
}
