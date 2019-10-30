package br.com.ufg.listaplic.repository;

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

    @Query( value = "SELECT * FROM application a" +
                "WHERE a.classroom_id = :classroomId" +
                "AND a.status = :ApplicationListStatus.ENCERRADA", nativeQuery = true )
    List<ListApplication> findByClassroomId(UUID classroomId);
}
