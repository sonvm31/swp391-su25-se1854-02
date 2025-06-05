package backend.healthrecord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.healthrecord.model.HealthRecord;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    Optional<HealthRecord> findByScheduleId(int scheduleId);
}