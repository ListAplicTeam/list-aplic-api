package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.ListApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListApplicationJpaRepository extends JpaRepository<ListApplication, UUID> {

    @Query(value = "SELECT * FROM application a " +
            "WHERE a.classroom_id IN (:classroomsId) " +
            "AND a.id NOT IN (SELECT application_id FROM answer WHERE user_id = :studentId)", nativeQuery = true)
    List<ListApplication> findByClassrooms(List<UUID> classroomsId, UUID studentId);

    List<ListApplication> findByClassroomAndStatus(UUID classroomId, ApplicationListStatus applicationListStatus);

    @Query(value = "SELECT ap.id, ap.list_id, classroom_id, question_id, answer, user_id" +
            "FROM application ap" +
            "JOIN answer aw on ap.list_id = aw.application_id" +
            "WHERE ap.classroom_id IN (:classroomsId)", nativeQuery = true)
    List<ListApplication> findByList(UUID listId);

}
