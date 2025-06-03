package backend.schedule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.schedule.dto.CreateScheduleRequest;
import backend.schedule.dto.UpdateCheckupScheduleRequest;
import backend.schedule.model.Schedule;
import backend.schedule.repository.ScheduleRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // Tạo ca khám bệnh
    public String create(CreateScheduleRequest request) {
        Schedule checkupSchedule = Schedule.builder()
                .date(request.date())
                .slot(request.slot())
                .doctor(userRepository.findById(request.doctorId()).get())
                .build();
        scheduleRepository.save(checkupSchedule);

        return "Checkup schedule created successfully with ID: " + checkupSchedule.getId() + ".";
    }

    // Xem danh sách ca khám bệnh 
    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }
 
    // Xem danh sách ca khám bệnh theo ID bệnh nhân
    public List<Schedule> getByPatientId(int patientId) {
        return scheduleRepository.findByPatientId(patientId);
    }

    // Xem danh sách ca khám bệnh theo ID bác sĩ
    public List<Schedule> getByDoctorId(int docotrId) {
        return scheduleRepository.findByDoctorId(docotrId);
    }
    
    // Xem chi tiết ca khám bệnh 
    public Schedule get(int id) {
        return scheduleRepository.findById(id).get();
    }

    // Chỉnh sửa ca khám bệnh
    public String update(int id, UpdateCheckupScheduleRequest request) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Check-up schedule not found with ID: " + id + ".");
        }

        Schedule CheckupSchedule = scheduleRepository.findById(id).get();
        Optional.of(request.date()).ifPresent(CheckupSchedule::setDate);
        Optional.of(request.slot()).ifPresent(CheckupSchedule::setSlot);
        Optional.of(request.status()).ifPresent(CheckupSchedule::setStatus);
        Optional.of(userRepository.findById(request.doctorId()).get()).ifPresent(CheckupSchedule::setDoctor);
    
        return "Check-up schedule updated successfully with ID: " + id + ".";
    }   

    // Đăng ký ca khám bệnh theo ID bệnh nhân
    public String register(int id, int patientId, String type) {
        Schedule CheckupSchedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Check-up schedule not found with ID: " + id + "."));
        Optional.of(userRepository.findById(patientId).get()).ifPresent(CheckupSchedule::setPatient);
        Optional.of(type).ifPresent(CheckupSchedule::setType);
        
        return "Check-up schedule registered successfully with ID: " + id + ".";
    }   

    // Xóa ca khám bệnh 
    public String delete(int id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Check-up slot not found with ID: " + id + ".");
        }
        
        scheduleRepository.delete(scheduleRepository.findById(id).get());
        
        return "Check-up slot deleted successfully with ID: " + id + ".";
    }
}