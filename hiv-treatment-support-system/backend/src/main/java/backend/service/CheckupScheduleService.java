package backend.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.CheckupSchedule;
import backend.repository.CheckupScheduleRepository;

@Service
@RequiredArgsConstructor
public class CheckupScheduleService {
    private final CheckupScheduleRepository checkupScheduleRepository;

    public Optional<CheckupSchedule> getCheckupScheduleById(int id) {
        return checkupScheduleRepository.findById(id);
    }

    public List<CheckupSchedule> getCheckupScheduleByUserId(int userId) {
        return checkupScheduleRepository.findCheckupScheduleByUserId(userId);
    }
}