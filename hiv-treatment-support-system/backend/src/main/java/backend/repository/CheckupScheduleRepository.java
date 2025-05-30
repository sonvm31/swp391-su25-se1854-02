package backend.repository;

import backend.model.CheckupSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CheckupScheduleRepository extends JpaRepository<CheckupSchedule, Integer> {
    ArrayList<CheckupSchedule> findCheckupScheduleByUser_id(String userID);

    int countByCheckup_id(String userID);
}
