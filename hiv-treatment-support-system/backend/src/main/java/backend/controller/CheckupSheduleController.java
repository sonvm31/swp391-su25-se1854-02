package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.CheckupSchedule;
import backend.service.CheckupScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class CheckupSheduleController {
    private final CheckupScheduleService checkupScheduleService;

    @RequestMapping("/list/{id}")
    public ResponseEntity<List<CheckupSchedule>> getCheckupScheduleByUserId(@RequestParam int userId) {
        List<CheckupSchedule> response = checkupScheduleService.getByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
