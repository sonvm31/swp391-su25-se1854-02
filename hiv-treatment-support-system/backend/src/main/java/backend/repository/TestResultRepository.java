package backend.repository;

import backend.model.TestResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TestResultRepository extends JpaRepository<TestResult, Integer> {
    public ArrayList<TestResult> findTestResultsByUser_id(int userID);

}
