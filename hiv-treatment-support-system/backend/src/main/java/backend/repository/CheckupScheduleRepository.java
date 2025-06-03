package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.CheckupSchedule;

@Repository
public interface CheckupScheduleRepository extends JpaRepository<CheckupSchedule, Integer> {
    List<CheckupSchedule> findByUserId(int userId);
}
