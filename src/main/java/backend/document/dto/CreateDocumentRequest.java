package backend.document.dto;

import java.time.LocalDate;

public record CreateDocumentRequest(
    String title,

    String author,

    String content,
    
    LocalDate createdAt
) {
}
