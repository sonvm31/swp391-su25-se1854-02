package backend.healthrecord.dto;

public record CreateHealthRecordRequest   (
    String roomCode,
    
    String insuranceNumber
){   
}
