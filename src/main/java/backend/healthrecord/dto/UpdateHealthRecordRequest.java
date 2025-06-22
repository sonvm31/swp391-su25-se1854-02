package backend.healthrecord.dto;

public record UpdateHealthRecordRequest(
    String hivStatus,

    String bloodType,

    String treatmentStatus,

    float weight,

    float height,

    Long scheduleId,
    
    Long regimenId
) {
}