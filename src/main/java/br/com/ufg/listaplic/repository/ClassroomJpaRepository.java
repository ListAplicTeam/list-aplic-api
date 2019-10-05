package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassroomJpaRepository extends JpaRepository<Classroom, UUID> {
    
}
