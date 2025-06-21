package backend.regimen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.regimen.model.Regimen;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Long> {
    public List<Regimen> findByDoctorId(long id);
}
