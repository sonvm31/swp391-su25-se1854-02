package backend.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateCheckupScheduleRequest (
    LocalDate date,

    LocalTime slot,
    
    String status,

    int doctorId
) {
}
