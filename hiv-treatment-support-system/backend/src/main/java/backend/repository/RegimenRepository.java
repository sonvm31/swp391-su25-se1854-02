package backend.repository;

import backend.model.Regimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Integer> {

}
