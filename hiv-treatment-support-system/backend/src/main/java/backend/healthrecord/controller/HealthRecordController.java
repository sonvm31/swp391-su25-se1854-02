package backend.healthrecord.controller;

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

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateHealthRecordRequest request) {
        String response = healthRecordService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{checkupId}")
    public ResponseEntity<HealthRecord> getByCheckupId(@PathVariable int checkupId) {
        HealthRecord response = healthRecordService.getByCheckupScheduleId(checkupId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateHealthRecordRequest request) {
        String response = healthRecordService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = healthRecordService.delete(id);
        return ResponseEntity.ok(response);
    }
}
