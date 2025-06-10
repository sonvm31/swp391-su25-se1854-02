package backend.documentimage.dto;

public record CreateDocumentImageRequest (
    String url,

    long documentId
) {   
}