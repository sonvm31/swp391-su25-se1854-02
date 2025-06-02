package backend.repository;

import backend.model.CheckupRecord;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckupRecordRepository extends JpaRepository<CheckupRecord, Integer> {
    Optional<CheckupRecord> findCheckupRecordByCheckupId(int checkupId);
}