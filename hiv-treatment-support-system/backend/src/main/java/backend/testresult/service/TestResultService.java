package backend.testresult.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.healthrecord.repository.HealthRecordRepository;
import backend.testresult.dto.CreateTestResultRequest;
import backend.testresult.dto.UpdateTestResultRequest;
import backend.testresult.model.TestResult;
import backend.testresult.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;
    private final HealthRecordRepository healthRecordRepository;

    // Tạo kết quả xét nghiệm 
    public String create(CreateTestResultRequest request) {
        var testResult = TestResult.builder()
            .type(request.type())
            .result(request.result())
            .unit(request.unit())
            .note(request.note())
            .expectedResultTime(request.expectedResultTime())
            .healthRecord(healthRecordRepository.findById(request.scheduleId()).get())
            .build();
        testResultRepository.save(testResult);

        return "Test result created successfully with ID: " + testResult.getId() + ".";
    }

    // Cập nhật kết quả xét nghiệm
    public String update(int id, UpdateTestResultRequest request) {
        TestResult testResult = testResultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Test result not found with ID: " + id + "."));
        
        Optional.of(request.type()).ifPresent(testResult::setType);
        Optional.of(request.result()).ifPresent(testResult::setResult);
        Optional.of(request.unit()).ifPresent(testResult::setUnit);
        Optional.of(request.note()).ifPresent(testResult::setNote);
        Optional.of(request.expectedResultTime()).ifPresent(testResult::setExpectedResultTime);
        Optional.of(request.actualResultTime()).ifPresent(testResult::setActualResultTime);
        testResultRepository.save(testResult);
        
        return "Test result updated successfully with ID: " + id + ".";
    }   
    
    // Xóa kết quả xét nghiệm
    public String delete(int id) {
        testResultRepository.delete(testResultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Test result not found with ID: " + id + ".")));

        return "Test result deleted successfully with ID: " + id + ".";
    }

    // Xem danh sách kết quả xét nghiệm theo ID phiếu phám sức khỏe
    public List<TestResult> list(int recordId) {
        List<TestResult> testResults = testResultRepository.findByHealthRecordId(recordId);
        if (testResults.isEmpty()) 
            throw new RuntimeException("Test result not found with record ID: " + recordId + ".");

        return testResults;
    }
}
