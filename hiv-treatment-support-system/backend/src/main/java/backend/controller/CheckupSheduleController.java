package backend.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.CheckupSchedule;
import backend.service.CheckupScheduleService;

@RestController
@RequestMapping("/api/checkup")
@RequiredArgsConstructor
public class CheckupSheduleController {
    private final CheckupScheduleService checkupScheduleService;

    @RequestMapping("/{id}/list")
    public ResponseEntity<List<CheckupSchedule>> getCheckupScheduleByUserId(@RequestParam int userId) {
        List<CheckupSchedule> response = checkupScheduleService.getCheckupScheduleByUserId(userId);
        return ResponseEntity.ok(response);
    }

}
