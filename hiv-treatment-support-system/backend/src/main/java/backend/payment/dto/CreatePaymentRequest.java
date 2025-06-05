package backend.payment.dto;

public record CreatePaymentRequest (
    String name,

    String account, 

    Boolean status, 

    String description, 

    Float amount,
    
    int scheduleId
) {
}
