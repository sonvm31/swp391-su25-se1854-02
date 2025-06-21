package backend.regimen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.regimen.model.Regimen;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, Long> {
    @Query("SELECT r FROM Regimen r WHERE r.doctor.id IS NULL OR r.doctor.id = :doctorId")
    List<Regimen> findByDoctorIdOrAll(@Param("doctorId") Long doctorId);
}
