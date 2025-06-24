package backend.testresult.controller;

import java.util.List;
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

import backend.testresult.dto.CreateTestResultRequest;
import backend.testresult.dto.UpdateTestResultRequest;
import backend.testresult.model.TestResult;
import backend.testresult.service.TestResultService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/test-result")
@RequiredArgsConstructor
public class TestResultController {
    private final TestResultService testResultService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateTestResultRequest request) {
        return ResponseEntity.ok(Map.of("message", testResultService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable long id, @RequestBody UpdateTestResultRequest request) {
        return ResponseEntity.ok(Map.of("message", testResultService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
        return ResponseEntity.ok(Map.of("message", testResultService.delete(id)));
    }

    @GetMapping("/health-record-id/{healthRecordId}")
    public ResponseEntity<List<TestResult>> get(@PathVariable long healthRecordId) {
        return ResponseEntity.ok(testResultService.list(healthRecordId));
    }
}
