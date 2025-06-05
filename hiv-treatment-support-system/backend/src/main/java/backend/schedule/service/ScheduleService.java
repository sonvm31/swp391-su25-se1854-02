package backend.schedule.service;

import java.time.LocalDate;
import java.time.LocalTime;
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

        return "Slot created successfully with ID: " + checkupSchedule.getId() + ".";
    }

    // Xem danh sách ca khám bệnh 
    public List<Schedule> list() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");

        return schedules;
    }
    
    // Xem chi tiết ca khám bệnh 
    public Schedule get(int id) {
        return scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + id + "."));
    }

    // Chỉnh sửa ca khám bệnh
    public String update(int id, UpdateCheckupScheduleRequest request) {        
        Schedule CheckupSchedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + id + "."));

        Optional.of(request.date()).ifPresent(CheckupSchedule::setDate);
        Optional.of(request.slot()).ifPresent(CheckupSchedule::setSlot);
        Optional.of(request.status()).ifPresent(CheckupSchedule::setStatus);
        Optional.of(userRepository.findById(request.doctorId()).get()).ifPresent(CheckupSchedule::setDoctor);
    
        return "Slot updated successfully with ID: " + id + ".";
    }   

    // Đăng ký ca khám bệnh theo ID bệnh nhân
    public String register(int id, int patientId, String type) {
        Schedule CheckupSchedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + id + "."));

        Optional.of(userRepository.findById(patientId).get()).ifPresent(CheckupSchedule::setPatient);
        Optional.of(type).ifPresent(CheckupSchedule::setType);
        
        return "Slot registered successfully with ID: " + id + ".";
    }   

    // Xóa ca khám bệnh 
    public String delete(int id) {
        scheduleRepository.delete(scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + id + ".")));
        
        return "Slot deleted successfully with ID: " + id + ".";
    }

    // Xem danh sách ca khám bệnh theo ID bệnh nhân
    public List<Schedule> getByPatientId(int patientId) {
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientId);
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");

        return schedules;
    }

    // Xem danh sách ca khám bệnh theo ID bác sĩ
    public List<Schedule> getByDoctorId(int docotrId) {
        List<Schedule> schedules = scheduleRepository.findByDoctorId(docotrId);
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");
            
        return schedules;
    }

    // Xem danh sách ca khám bệnh theo loại
    public List<Schedule> getByType(String type) {
        List<Schedule> schedules = scheduleRepository.findByType(type);
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");
        
        return schedules;
    }

    // Xem danh sách ca khám bệnh theo trạng thái
    public List<Schedule> getByStatus(String status) {
        List<Schedule> schedules = scheduleRepository.findByStatus(status);
        if (schedules.isEmpty())
            throw new RuntimeException("No slot found.");
       
        return schedules;
    }

    // Xem danh sách ca khám bệnh theo ngày
    public List<Schedule> getByDate(LocalDate date) {
        List<Schedule> schedules = scheduleRepository.findByDate(date);
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");
        
        return schedules;
    }

    // Xem danh sách ca khám bệnh theo giờ
    public List<Schedule> getBySlot(LocalTime slot) {
        List<Schedule> schedules = scheduleRepository.findBySlot(slot);
        if (schedules.isEmpty()) 
            throw new RuntimeException("No slot found.");

        return schedules;
    }
}