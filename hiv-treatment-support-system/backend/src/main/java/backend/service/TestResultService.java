package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.TestResult;
import backend.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;

    public Optional<TestResult> getRegimenById(int id) {
        if (testResultRepository.existsById(id)) {
            throw new RuntimeException("Regimen not found with ID: " + id + ".");
        }
        return testResultRepository.findById(id);
    }
}
