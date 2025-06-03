package backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.CheckupRecord;

@Repository
public interface CheckupRecordRepository extends JpaRepository<CheckupRecord, Integer> {
    Optional<CheckupRecord> findByCheckupId(int checkupId);
}