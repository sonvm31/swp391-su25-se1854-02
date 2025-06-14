package backend.schedule.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.schedule.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPatientId(long userId);

    List<Schedule> findByDoctorId(long userId);

    List<Schedule> findByType(String type);

    List<Schedule> findByStatus(String status);

    List<Schedule> findByDate(LocalDate date);

    List<Schedule> findBySlot(LocalTime slot);
}
