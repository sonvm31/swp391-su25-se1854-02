package backend.testresult.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.healthrecord.repository.HealthRecordRepository;
import backend.testresult.dto.CreateTestResultRequest;
import backend.testresult.dto.UpdateTestResultRequest;
import backend.testresult.model.TestResult;
import backend.testresult.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestResultService {
    @Autowired
    private final TestResultRepository testResultRepository;

    @Autowired
    private final HealthRecordRepository healthRecordRepository;

    // Create test result
    public String create(CreateTestResultRequest request) {
        var testResult = TestResult.builder()
            .type(request.type())
            .note(request.note())
            .expectedResultTime(request.expectedResultTime())
            .healthRecord(healthRecordRepository.findById(request.healthRecordId()).get())
            .build();
        testResultRepository.save(testResult);

        return "TEST RESULT CREATED SUCCESSFULLY WITH ID: " + testResult.getId();
    }

    // List test results
    public List<TestResult> list() {
        return testResultRepository.findAll();
    }

    // Update test result
    public String update(long id, UpdateTestResultRequest request) {
        TestResult testResult = testResultRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO TEST RESULT FOUND WITH ID: " + id));
        
        Optional.ofNullable(request.type()).ifPresent(testResult::setType);
        Optional.ofNullable(request.result()).ifPresent(testResult::setResult);
        Optional.ofNullable(request.unit()).ifPresent(testResult::setUnit);
        Optional.ofNullable(request.note()).ifPresent(testResult::setNote);
        Optional.ofNullable(request.expectedResultTime()).ifPresent(testResult::setExpectedResultTime);
        Optional.ofNullable(request.actualResultTime()).ifPresent(testResult::setActualResultTime);
        testResultRepository.save(testResult);
        
        return "TEST RESULT UPDATED SUCCESSFULLY WITH ID: " + id;
    }   
    
    // Delete test result
    public String delete(long id) {
        testResultRepository.delete(testResultRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO TEST RESULT FOUND WITH ID: " + id)));

        return "TEST RESULT DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // List test results by health record ID
    public List<TestResult> list(long recordId) {
        return testResultRepository.findByHealthRecordId(recordId);
    }
}
