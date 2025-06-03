package backend.model.request;

public record CreateCheckupRecordRequest   (
    String roomCode,
    String insuranceNumber,
    String note
){   
}
