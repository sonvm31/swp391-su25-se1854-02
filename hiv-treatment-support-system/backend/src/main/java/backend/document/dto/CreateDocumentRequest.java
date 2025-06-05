package backend.document.dto;

import java.time.LocalDateTime;

public record CreateDocumentRequest(
    String title,

    String author,

    String content,
    
    LocalDateTime createdAt
) {
}
