package br.com.ufg.listaplic.repository;

import br.com.ufg.listaplic.model.ListApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListApplicationJpaRepository extends JpaRepository<ListApplication, UUID> {
}
