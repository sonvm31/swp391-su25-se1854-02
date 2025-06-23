package backend.healthrecord.dto;

public record CreateHealthRecordRequest(

        long scheduleId,
        String roomCode,

        String insuranceNumber) {
}
