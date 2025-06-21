package backend.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateScheduleRequest (
    LocalDate date,

    LocalTime slot,
    
    String roomCode,
    
    String status,

    long doctorId
) {
}
