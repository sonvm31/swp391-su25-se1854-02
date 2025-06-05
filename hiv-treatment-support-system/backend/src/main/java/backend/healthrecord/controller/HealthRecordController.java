package backend.healthrecord.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.healthrecord.dto.CreateHealthRecordRequest;
import backend.healthrecord.dto.UpdateHealthRecordRequest;
import backend.healthrecord.model.HealthRecord;
import backend.healthrecord.service.HealthRecordService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class HealthRecordController {
    private final HealthRecordService healthRecordService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateHealthRecordRequest request) {
        return ResponseEntity.ok(Map.of("message", healthRecordService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable int id, @RequestBody UpdateHealthRecordRequest request) {
        return ResponseEntity.ok(Map.of("message", healthRecordService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("message", healthRecordService.delete(id)));
    }
    
    @GetMapping("/schedule-id/{scheduleId}")
    public ResponseEntity<HealthRecord> getByCheckupId(@PathVariable int checkupId) {
        HealthRecord response = healthRecordService.getByCheckupScheduleId(checkupId);
        return ResponseEntity.ok(response);
    }
}
