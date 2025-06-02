package backend.service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.TestResult;
import backend.repository.TestResultRepository;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;

    public Optional<TestResult> getRegimenById(int id) {
        return testResultRepository.findById(id);
    }
}
