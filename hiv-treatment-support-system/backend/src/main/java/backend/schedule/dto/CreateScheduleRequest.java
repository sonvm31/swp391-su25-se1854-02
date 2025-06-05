package backend.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateScheduleRequest (
    String type,

    String status,

    LocalDate date,

    LocalTime slot,
    
    int doctorId
){    
}
