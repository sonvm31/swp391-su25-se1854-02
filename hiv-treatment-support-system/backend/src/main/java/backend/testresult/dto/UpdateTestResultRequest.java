package backend.testresult.dto;

import java.time.LocalDateTime;

public record UpdateTestResultRequest(
    String type,

    String result,

    String unit,

    String note,
    
    LocalDateTime expectedResultTime,

    LocalDateTime actualResultTime
) {
}
