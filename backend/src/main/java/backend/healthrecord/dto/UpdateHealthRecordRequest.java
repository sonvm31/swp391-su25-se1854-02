package backend.healthrecord.dto;

public record UpdateHealthRecordRequest(
    String roomCode,

    String insuranceNumber,

    String hivStatus,

    String bloodType,

    String note,

    String treatmentStatus,

    float weight,

    float height,

    int scheduleId,
    
    int regimenId
) {
}