package backend.regimen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.regimen.model.Regimen;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Integer> {
}
