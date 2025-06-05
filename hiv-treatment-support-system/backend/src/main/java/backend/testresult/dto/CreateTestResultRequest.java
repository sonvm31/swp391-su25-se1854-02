package backend.testresult.dto;

import java.time.LocalDateTime;

public record CreateTestResultRequest(
    String type,

    String result,

    String unit,

    String note,

    LocalDateTime expectedResultTime,
    
    int scheduleId
) {
}
