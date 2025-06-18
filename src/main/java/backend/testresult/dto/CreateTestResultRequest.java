package backend.testresult.dto;

import java.time.LocalDateTime;

public record CreateTestResultRequest(
    String type,

    String note,

    LocalDateTime expectedResultTime,
    
    long healthRecordId
) {
}
