package backend.schedule.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.payment.service.VNPayService;
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

    @Autowired
    private VNPayService vnpayService;

    // Tạo ca khám bệnh
    public String create(CreateScheduleRequest request) {

        List<Object[]> slotCounts = scheduleRepository.findSlotCountsByDoctorAndDate(request.doctorId(),
                request.date());
        Map<LocalTime, Long> slotCountMap = slotCounts.stream()
                .collect(Collectors.toMap(
                        result -> (LocalTime) result[0],
                        result -> (Long) result[1]));

        if (slotCountMap.getOrDefault(request.slot(), 0L) >= 5) {
            throw new IllegalStateException("Slot " + request.slot() + " đã đủ 5 lịch hẹn.");
        }

        Schedule checkupSchedule = Schedule.builder()

                .date(request.date())
                .slot(request.slot())
                .doctor(userRepository.findById(request.doctorId()).get())
                .status("ACTIVE")
                .build();

        scheduleRepository.save(checkupSchedule);

        return "SLOT CREATED SUCCESSFULLY WITH ID: " + checkupSchedule.getId();
    }

    public List<Schedule> getSchedulesByDoctorDateAndStatus(Long doctorId, LocalDate date, String status) {
        return scheduleRepository.findAvailableSchedulesByDoctorAndDate(doctorId, date)
                .stream()
                .filter(schedule -> status == null || schedule.getStatus().equals(status))
                .collect(Collectors.toList());
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

    // lấy những slot còn trống
    public List<String> getAvailableSlot(Long doctorId, LocalDate date) {
        List<LocalTime> allSlots = Arrays.asList(
                LocalTime.of(8, 0), LocalTime.of(8, 30), LocalTime.of(9, 0), LocalTime.of(9, 30),
                LocalTime.of(10, 0), LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30),
                LocalTime.of(12, 0), LocalTime.of(12, 30), LocalTime.of(13, 0), LocalTime.of(13, 30),
                LocalTime.of(14, 0), LocalTime.of(14, 30), LocalTime.of(15, 0), LocalTime.of(15, 30),
                LocalTime.of(16, 0), LocalTime.of(16, 30));

        List<Object[]> slotCounts = scheduleRepository.findSlotCountsByDoctorAndDate(doctorId, date);

        Map<LocalTime, Long> slotCountMap = slotCounts.stream()
                .collect(Collectors.toMap(
                        result -> (LocalTime) result[0],
                        result -> (Long) result[1],
                        (v1, v2) -> v1));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<String> availableSlots = allSlots.stream()
                .filter(slot -> {
                    Long count = slotCountMap.getOrDefault(slot, 0L);
                    return count < 5;
                })
                .map(slot -> slot.format(formatter))
                .collect(Collectors.toList());

        return availableSlots;
    }

    // Khởi tạo thanh toán
    public String initiatePayment(Long scheduleId, String amount, String ipAddress)
            throws UnsupportedEncodingException, Exception {
        return vnpayService.createPaymentUrl(scheduleId, amount, ipAddress);
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
        CheckupSchedule.setStatus("PENDING");
        scheduleRepository.save(CheckupSchedule);

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