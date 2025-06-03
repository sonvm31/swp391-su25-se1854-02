package backend.testresult.controller;

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

import backend.testresult.dto.CreateTestResultRequest;
import backend.testresult.dto.UpdateTestResultRequest;
import backend.testresult.model.TestResult;
import backend.testresult.service.TestResultService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/result")
@RequiredArgsConstructor
public class TestResultController {
    private final TestResultService testResultService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateTestResultRequest request) {
        String response = testResultService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{checkupId}")
    public ResponseEntity<List<TestResult>> get(@PathVariable int checkupId) {
        List<TestResult> response = testResultService.list(checkupId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateTestResultRequest request) {
        String response = testResultService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = testResultService.delete(id);
        return ResponseEntity.ok(response);
    }
}
