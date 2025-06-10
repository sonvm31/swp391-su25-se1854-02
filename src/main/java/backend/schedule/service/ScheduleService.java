package backend.schedule.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.schedule.dto.CreateScheduleRequest;
import backend.schedule.dto.UpdateCheckupScheduleRequest;
import backend.schedule.model.Schedule;
import backend.schedule.repository.ScheduleRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    @Autowired
    private final ScheduleRepository scheduleRepository;

    @Autowired
    private final UserRepository userRepository;

    // Tạo ca khám bệnh
    public String create(CreateScheduleRequest request) {
        Schedule checkupSchedule = Schedule.builder()
            .date(request.date())
            .slot(request.slot())
            .doctor(userRepository.findById(request.doctorId()).get())
            .build();
        scheduleRepository.save(checkupSchedule);

        return "SLOT CREATED SUCCESSFULLY WITH ID: " + checkupSchedule.getId();
    }

    // Xem danh sách ca khám bệnh 
    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }
    
    // Xem chi tiết ca khám bệnh 
    public Schedule get(long id) {
        return scheduleRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SLOT FOUND WITH ID: " + id));
    } 

    // Chỉnh sửa ca khám bệnh
    public String update(long id, UpdateCheckupScheduleRequest request) {        
        Schedule checkupSchedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SLOT FOUND WITH ID" + id));

        Optional.of(request.date()).ifPresent(checkupSchedule::setDate);
        Optional.of(request.slot()).ifPresent(checkupSchedule::setSlot);
        Optional.of(request.status()).ifPresent(checkupSchedule::setStatus);
        Optional.of(userRepository.findById(request.doctorId()).get()).ifPresent(checkupSchedule::setDoctor);
    
        return "SLOT UPDATED SUCCESSFULLY WITH ID: " + id;
    }   

    // Đăng ký ca khám bệnh theo ID bệnh nhân
    public String register(long id, long patientId, String type) {
        Schedule CheckupSchedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SLOT FOUND WITH ID: " + id));

        Optional.of(userRepository.findById(patientId).get()).ifPresent(CheckupSchedule::setPatient);
        Optional.of(type).ifPresent(CheckupSchedule::setType);
        
        return "SLOT REGISTERED SUCCESSFULLY WITH ID: " + id;
    }   

    // Xóa ca khám bệnh 
    public String delete(long id) {
        scheduleRepository.delete(scheduleRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SLOT FOUND WITH ID: " + id)));
        
        return "SLOT DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Xem danh sách ca khám bệnh theo ID bệnh nhân
    public List<Schedule> getByPatientId(long patientId) {
        return scheduleRepository.findByPatientId(patientId);
    }

    // Xem danh sách ca khám bệnh theo ID bác sĩ
    public List<Schedule> getByDoctorId(long doctorId) {
        return scheduleRepository.findByDoctorId(doctorId);
    }

    // Xem danh sách ca khám bệnh theo loại
    public List<Schedule> getByType(String type) {
        return scheduleRepository.findByType(type);
    }

    // Xem danh sách ca khám bệnh theo trạng thái
    public List<Schedule> getByStatus(String status) {
        return scheduleRepository.findByStatus(status);
    }

    // Xem danh sách ca khám bệnh theo ngày
    public List<Schedule> getByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    // Xem danh sách ca khám bệnh theo giờ
    public List<Schedule> getBySlot(LocalTime slot) {
        return scheduleRepository.findBySlot(slot);
    }
}