package backend.repository;

import backend.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {
    TestResult findTestResultByRecordId(int recordId);
}
