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

    public Optional<CheckupSchedule> getCheckupScheduleById(int id) {
        return checkupScheduleRepository.findById(id);
    }

    public List<CheckupSchedule> getCheckupScheduleByUserId(int userId) {
        return checkupScheduleRepository.findCheckupScheduleByUserId(userId);
    }

    public void createCheckupSchedule(LocalDate date, LocalTime slot, int doctorId) {
        CheckupSchedule checkupSchedule = CheckupSchedule.builder()
                .date(date)
                .slot(slot)
                .user(userRepository.findById(doctorId).get())
                .build();
        checkupScheduleRepository.save(checkupSchedule);
    }
}