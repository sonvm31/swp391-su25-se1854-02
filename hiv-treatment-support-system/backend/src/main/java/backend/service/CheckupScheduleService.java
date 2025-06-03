package backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.CheckupSchedule;
import backend.repository.CheckupScheduleRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckupScheduleService {
    private final CheckupScheduleRepository checkupScheduleRepository;
    private final UserRepository userRepository;

    // --- Phần tương tác người dùng ---
    // Lấy ca khám bệnh và kiểm tra xem ca đã đầy người đăng ký chưa
    public Optional<CheckupSchedule> getForRegister(int id) {
        CheckupSchedule slot = checkupScheduleRepository.findById(id).get();
        List<CheckupSchedule> list = checkupScheduleRepository.findAll();
        int existCount = 0;
        for (CheckupSchedule existSlot : list) {
            if (slot.getDate().equals(existSlot.getDate())
            || slot.getSlot().equals(existSlot.getSlot())) {
                existCount++;
            }
        }
        if (existCount == 5) {
            throw new RuntimeException("This slot has full");
        }
        return Optional.of(slot);
    }

    // Hiển thị tất cả ca khám bệnh đã đăng kí
    public List<CheckupSchedule> getByUserId(int userId) {
        return checkupScheduleRepository.findByUserId(userId);
    }

    // --- Phần tương tác quản lí --- 
    // Tạo ca khám bệnh
    public String create(LocalDate date, LocalTime slot, int doctorId) {
        CheckupSchedule checkupSchedule = CheckupSchedule.builder()
                .date(date)
                .slot(slot)
                .user(userRepository.findById(doctorId).get())
                .build();
        checkupScheduleRepository.save(checkupSchedule);
        return "Checkup schedule created successfully with ID: " + checkupSchedule.getId() + ".";
    }

    // Xem thông tin cá khám
        public Optional<CheckupSchedule> get(int id) {
        return checkupScheduleRepository.findById(id);
    }
}