package backend.healthrecord.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.healthrecord.dto.CreateHealthRecordRequest;
import backend.healthrecord.dto.UpdateHealthRecordRequest;
import backend.healthrecord.model.HealthRecord;
import backend.healthrecord.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    @Autowired
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
       return healthRecordRepository.findAll();
    }
    
    // Xem chi tiết phiếu khám sức khỏe 
    public HealthRecord get(long id) {
        return healthRecordRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO HEALTH RECORD FOUND WITH ID: " + id));
    }

    // Cập nhật phiếu khám sức khỏe
    public String update(long id, UpdateHealthRecordRequest request) {
        HealthRecord record = healthRecordRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO HEALTH RECORD FOUND WITH ID: " + id));
        
        Optional.ofNullable(request.roomCode()).ifPresent(record::setRoomCode);
        Optional.ofNullable(request.insuranceNumber()).ifPresent(record::setRoomCode);
        Optional.ofNullable(request.insuranceNumber()).ifPresent(record::setInsuranceNumber);
        Optional.ofNullable(request.hivStatus()).ifPresent(record::setHivStatus);
        Optional.ofNullable(request.bloodType()).ifPresent(record::setBloodType);
        Optional.ofNullable(request.note()).ifPresent(record::setNote);
        Optional.ofNullable(request.weight()).ifPresent(record::setWeight);
        Optional.ofNullable(request.height()).ifPresent(record::setHeight);
        Optional.ofNullable(request.treatmentStatus()).ifPresent(record::setTreatmentStatus);
        healthRecordRepository.save(record);

        return "Check-up record updated successfullty with ID: " + record.getId() + ".";
    }

    // Xóa phiếu khám sức khỏe
    public String delete(long id) {
        healthRecordRepository.delete(healthRecordRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO HEALTH RECORD FOUND WITH ID: " + id)));
        
        return "Check-up record deleted successfully with ID:" + id + ".";
    }

    // Xem phiếu khám sức khỏe theo ID ca khám
    public HealthRecord getByScheduleId(long scheduleId) {
        return healthRecordRepository.findByScheduleId(scheduleId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO HEALTH RECORD FOUND WITH SCHEDULE ID: " + scheduleId));
    }
}
