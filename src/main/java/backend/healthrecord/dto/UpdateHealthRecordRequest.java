package backend.healthrecord.dto;

public record UpdateHealthRecordRequest(
    String insuranceNumber,

    String hivStatus,

    String bloodType,

    String treatmentStatus,

    float weight,

    float height,

    int scheduleId,
    
    int regimenId
) {
}