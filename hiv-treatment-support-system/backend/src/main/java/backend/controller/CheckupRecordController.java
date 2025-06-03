package backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.CheckupRecord;
import backend.model.request.CreateCheckupRecordRequest;
import backend.service.CheckupRecordService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
public class CheckupRecordController {
    private final CheckupRecordService checkupRecordService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateCheckupRecordRequest request) {
        String response = checkupRecordService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckupRecord> get(@PathVariable int id) {
        CheckupRecord response = checkupRecordService.get(id).get();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = checkupRecordService.delete(id);
        return ResponseEntity.ok(response);
    }
}
