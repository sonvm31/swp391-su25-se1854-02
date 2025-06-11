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

        return "TEST RESULT CREATED SUCCESSFULLY WITH ID: " + testResult.getId();
    }

    // Cập nhật kết quả xét nghiệm
    public String update(long id, UpdateTestResultRequest request) {
        TestResult testResult = testResultRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO TEST RESULT FOUND WITH ID: " + id));
        
        Optional.of(request.type()).ifPresent(testResult::setType);
        Optional.of(request.result()).ifPresent(testResult::setResult);
        Optional.of(request.unit()).ifPresent(testResult::setUnit);
        Optional.of(request.note()).ifPresent(testResult::setNote);
        Optional.of(request.expectedResultTime()).ifPresent(testResult::setExpectedResultTime);
        Optional.of(request.actualResultTime()).ifPresent(testResult::setActualResultTime);
        testResultRepository.save(testResult);
        
        return "TEST RESULT UPDATED SUCCESSFULLY WITH ID: " + id;
    }   
    
    // Xóa kết quả xét nghiệm
    public String delete(long id) {
        testResultRepository.delete(testResultRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO TEST RESULT FOUND WITH ID: " + id)));

        return "TEST RESULT DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Xem danh sách kết quả xét nghiệm theo ID phiếu phám sức khỏe
    public List<TestResult> list(long recordId) {
        return testResultRepository.findByHealthRecordId(recordId);
    }
}
