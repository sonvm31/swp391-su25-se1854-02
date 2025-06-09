package backend.notification.dto;

import java.time.LocalDateTime;

public record  CreateNotificationRequest(
    String title,

    String message,
    
    LocalDateTime createdAt,
    
    long userId
) {
}
