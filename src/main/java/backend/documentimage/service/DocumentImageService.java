package backend.documentimage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.document.model.Document;
import backend.document.repository.DocumentRepository;
import backend.documentimage.dto.CreateDocumentImageRequest;
import backend.documentimage.dto.UpdateDocumentImageRequest;
import backend.documentimage.model.DocumentImage;
import backend.documentimage.repository.DocumentImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentImageService {
    @Autowired
    private final DocumentImageRepository documentImageRepository;

    @Autowired
    private final DocumentRepository documentRepository;

    // Tạo ảnh tài liệu
    public String create(CreateDocumentImageRequest request) {
        Document document = documentRepository.findById(request.documentId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + request.documentId()));

        DocumentImage documentImage = DocumentImage.builder()
            .url(request.url())
            .document(document)
            .build();

        documentImageRepository.save(documentImage);

        return "DOCUMENT IMAGE CREATED SUCCESSFULLY WITH ID: " + documentImage.getId();
    }

    // Danh sách ảnh tài liệu
    public List<DocumentImage> list() {
        return documentImageRepository.findAll();
    }

    // Chi tiết ảnh tài liệu
    public DocumentImage get(long id) {
        return documentImageRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT IMAGE FOUND WITH ID: " + id));
    }

    // Cập nhật ảnh tài liệu
    public String update(long id, UpdateDocumentImageRequest request) {
        DocumentImage documentImage = documentImageRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT IMAGE FOUND WITH ID: " + id));

        Optional.of(request.url()).ifPresent(documentImage::setUrl);

        Document document = documentRepository.findById(request.documentId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + request.documentId()));
        documentImage.setDocument(document);

        documentImageRepository.save(documentImage);

        return "DOCUMENT IMAGE UPDATED SUCCESSFULLY WITH ID: " + id;
    }

    // Xóa ảnh tài liệu
     public String delete(long id) {
        DocumentImage documentImage = documentImageRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT IMAGE FOUND WITH ID: " + id));

        documentImageRepository.delete(documentImage);

        return "DOCUMENT IMAGE DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Xem danh sách ảnh tài liệu theo ID tài liệu
    public List<DocumentImage> getByDocumentId(long id) {
        return documentImageRepository.findByDocumentId(id);
    }
}