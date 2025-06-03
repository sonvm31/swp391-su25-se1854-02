package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.CheckupRecord;
import backend.model.request.CreateCheckupRecordRequest;
import backend.repository.CheckupRecordRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckupRecordService {
    private final CheckupRecordRepository checkupRecordRepository;  

    // Nhân viên tạo phiếu khám sức khỏe
    public String create(CreateCheckupRecordRequest request) {
        var checkupRecord = CheckupRecord.builder()
        .roomCode(request.roomCode())
        .insuranceNumber(request.insuranceNumber())
        .note(request.note())
        .build();
        checkupRecordRepository.save(checkupRecord);
        return "Check-up record created with ID: " + checkupRecord.getId() + ".";
    }

    // Bệnh nhân xem phiếu khám sức khỏe
    public Optional<CheckupRecord> get(int id) {
        if (checkupRecordRepository.existsById(id)) {
            throw new RuntimeException("Check-up record not found with ID: " + id + ".");
        }
        return checkupRecordRepository.findByCheckupId(id);
    }

    // 
    public String delete(int id) {
        if (checkupRecordRepository.existsById(id)) {
            throw new RuntimeException("Check-up record not found with ID:" + id + ".");
        }
        checkupRecordRepository.delete(checkupRecordRepository.findById(id).get());
        return "Check-up record deleted successfully with ID:" + id + ".";
    }
}
