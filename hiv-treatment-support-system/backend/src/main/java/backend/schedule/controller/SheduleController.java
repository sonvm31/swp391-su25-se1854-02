package backend.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.schedule.dto.CreateScheduleRequest;
import backend.schedule.dto.UpdateCheckupScheduleRequest;
import backend.schedule.model.Schedule;
import backend.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class SheduleController {
    private final ScheduleService checkupScheduleService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateScheduleRequest request) {
        String response = checkupScheduleService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Schedule>> list() {
        List<Schedule> response = checkupScheduleService.list();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{doctorId}/list")
    public ResponseEntity<List<Schedule>> getByDoctorId(@PathVariable int id) {
        List<Schedule> response = checkupScheduleService.getByDoctorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{patientId}/list")
    public ResponseEntity<List<Schedule>> getByPatientId(@PathVariable int id) {
        List<Schedule> response = checkupScheduleService.getByPatientId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> list(@PathVariable int id) {
        Schedule response = checkupScheduleService.get(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update") 
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateCheckupScheduleRequest request) {
        String response = checkupScheduleService.update(id, request);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}/register") 
    public ResponseEntity<String> register(@PathVariable int id, @RequestBody int patientId, String type) {
        String response = checkupScheduleService.register(id, patientId, type);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = checkupScheduleService.delete(id);
        return ResponseEntity.ok(response);
    }
}
