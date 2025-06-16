package backend.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateScheduleRequest(
        String type,

        LocalDate date,

        LocalTime slot,

        long doctorId) {
}
