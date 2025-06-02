package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.CheckupRecord;
import backend.repository.CheckupRecordRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckupRecordService {
    private final CheckupRecordRepository checkupRecordRepository;  

    public Optional<CheckupRecord> getCheckupRecordByCheckupId(int checkupId) {
        return checkupRecordRepository.findCheckupRecordByCheckupId(checkupId);
    }

    public Boolean deleteCheckupRecordById(int id) {
        if (checkupRecordRepository.findById(id).get() != null) {
            checkupRecordRepository.delete(checkupRecordRepository.findById(id).get());
            return true;
        }
        return false;
    }
}
