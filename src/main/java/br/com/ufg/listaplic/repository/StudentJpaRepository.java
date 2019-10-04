package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, UUID> {
    
}
