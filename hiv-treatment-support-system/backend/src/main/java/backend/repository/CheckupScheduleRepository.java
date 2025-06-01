package backend.repository;

import backend.model.CheckupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckupScheduleRepository extends JpaRepository<CheckupSchedule, Integer> {
    CheckupSchedule findCheckupScheduleByUserId(int userId);
}
