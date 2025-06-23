package backend.payment.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long scheduleId;
    private String amount;
}
