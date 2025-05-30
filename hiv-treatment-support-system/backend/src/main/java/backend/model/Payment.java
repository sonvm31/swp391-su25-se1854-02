package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Payment {
    private int paymentID;
    private String account;
    private float amount;
    private String description;
    private boolean status;
}
