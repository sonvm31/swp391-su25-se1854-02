package backend.documentimage.dto;

public record UpdateDocumentImageRequest (
    String url,

    long documentId
) {   
}