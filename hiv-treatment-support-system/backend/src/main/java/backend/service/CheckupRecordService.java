package backend.service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.CheckupRecord;
import backend.repository.CheckupRecordRepository;

@Service
@RequiredArgsConstructor
public class CheckupRecordService {
    private final CheckupRecordRepository checkupRecordRepository;

    public Optional<CheckupRecord> getCheckupRepcordById(int id) {
        return checkupRecordRepository.findById(id);
    }

    public Optional<CheckupRecord> getCheckupRecordByCheckupId(int checkupId) {
        return checkupRecordRepository.findCheckupRecordByCheckupId(checkupId);
    }
}
