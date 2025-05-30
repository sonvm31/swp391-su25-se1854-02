package backend.repository;

import backend.dto.UserPrivateInformationRequest;
import backend.model.CheckupRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CheckupRecordRepository extends JpaRepository<CheckupRecord, Integer> {
    public ArrayList<CheckupRecord> findCheckupRercordByUserID(UserPrivateInformationRequest request);
}