package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Notification {
    private int notificationID;
    private String title;
    private String message;
    private int userID;
}
