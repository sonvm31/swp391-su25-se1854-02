package backend.document.dto;

public record UpdateDocumentRequest(
    String title,

    String author,
    
    String content
) {
}
