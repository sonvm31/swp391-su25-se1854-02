package backend.healthrecord.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.healthrecord.dto.CreateHealthRecordRequest;
import backend.healthrecord.dto.UpdateHealthRecordRequest;
import backend.healthrecord.model.HealthRecord;
import backend.healthrecord.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;  

    // Tạo phiếu khám sức khỏe
    public String create(CreateHealthRecordRequest request) {
        var healthRecord = HealthRecord.builder()
            .roomCode(request.roomCode())
            .insuranceNumber(request.insuranceNumber())
            .build();
        healthRecordRepository.save(healthRecord);

        return "Check-up record created with ID: " + healthRecord.getId() + ".";
    }

    // Xem danh sách phiếu khám sức khỏe
    public List<HealthRecord> list() {
        List<HealthRecord> healthRecords = healthRecordRepository.findAll();
        if (healthRecords.isEmpty()) 
            throw new RuntimeException("No health record found.");
        return healthRecords;
    }
    
    // Xem phiếu khám sức khỏe theo ID ca khám
    public HealthRecord getByCheckupScheduleId(int id) {
        return healthRecordRepository.findByScheduleId(id)
            .orElseThrow(() -> new RuntimeException("Health record not found with ID: " + id +"."));
    }

    // Xem chi tiết phiếu khám sức khỏe 
    public HealthRecord get(int id) {
        return healthRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Health record not found with ID: " + id +"."));
    }

    // Cập nhật phiếu khám sức khỏe
    public String update(int id, UpdateHealthRecordRequest request) {
        HealthRecord record = healthRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Check-up record not found with ID: " + id + "."));
        
        Optional.ofNullable(request.roomCode()).ifPresent(record::setRoomCode);
        Optional.ofNullable(request.insuranceNumber()).ifPresent(record::setRoomCode);
        Optional.ofNullable(request.insuranceNumber()).ifPresent(record::setInsuranceNumber);
        Optional.ofNullable(request.hivStatus()).ifPresent(record::setHivStatus);
        Optional.ofNullable(request.bloodType()).ifPresent(record::setBloodType);
        Optional.ofNullable(request.note()).ifPresent(record::setNote);
        Optional.ofNullable(request.weight()).ifPresent(record::setWeight);
        Optional.ofNullable(request.height()).ifPresent(record::setHeight);
        record.setFinishedTreatment(request.isFinishedTreatment());
        healthRecordRepository.save(record);

        return "Check-up record updated successfullty with ID: " + record.getId() + ".";
    }

    // Xóa phiếu khám sức khỏe
    public String delete(int id) {
        healthRecordRepository.delete(healthRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Check-up record not found with ID:" + id + ".")));
        
        return "Check-up record deleted successfully with ID:" + id + ".";
    }
}
