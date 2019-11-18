package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, UUID> {

    @Query("SELECT count(e) > 0 FROM Enrollment e " +
            "WHERE e.student.id = :studentId " +
            "AND e.classroom.id = :classroomId")
    boolean isEnrolled(UUID classroomId, UUID studentId);

    Integer countStudentsByClassroomId(UUID classroomId);
}
