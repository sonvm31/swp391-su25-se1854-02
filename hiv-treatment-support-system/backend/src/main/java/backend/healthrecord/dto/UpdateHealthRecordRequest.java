package backend.healthrecord.dto;

public record UpdateHealthRecordRequest(
    String roomCode,

    String insuranceNumber,

    String hivStatus,

    String bloodType,

    String note,

    float weight,

    float height,

    boolean isFinishedTreatment,

    int scheduleId,
    
    int regimenId
) {
}