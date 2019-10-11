package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassroomJpaRepository extends JpaRepository<Classroom, UUID> {

    Optional<Classroom> findByCode(String code);

    List<Classroom> findByInstructorId(UUID instructorId);

    @Query("SELECT c FROM Classroom c " +
            "LEFT JOIN FETCH c.enrollments e " +
            "WHERE e.student.id = :studentId")
    List<Classroom> findByStudentId(UUID studentId);
    
}
