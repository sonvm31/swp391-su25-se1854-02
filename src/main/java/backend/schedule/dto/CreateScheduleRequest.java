package backend.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateScheduleRequest(
        String type,

        String roomCode,
        
        LocalDate date,

        LocalTime slot,

        long doctorId) {
}
