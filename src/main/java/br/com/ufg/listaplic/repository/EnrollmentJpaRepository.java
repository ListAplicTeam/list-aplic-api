package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, UUID> {
    
}
