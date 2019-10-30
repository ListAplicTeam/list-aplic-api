package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByEmail(String email);

    @Query(value = "SELECT s FROM Student s " +
            "LEFT JOIN FETCH s.enrollments e " +
            "WHERE e.classroom.id = :classroomId")
    List<Student> findStudentsByClassroomId(UUID classroomId);
}
