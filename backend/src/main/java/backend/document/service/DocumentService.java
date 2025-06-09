package backend.document.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.document.dto.CreateDocumentRequest;
import backend.document.dto.UpdateDocumentRequest;
import backend.document.model.Document;
import backend.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {
    @Autowired
    private final DocumentRepository documentRepository;

    // Tạo tài liệu
    public String create(CreateDocumentRequest request) {
        var document = Document.builder()
            .title(request.title())
            .author(request.author())
            .content(request.content())
            .build();
        documentRepository.save(document);

        return "Document created successfully with ID: " + document.getId() + ".";
    }

    // Xem danh sách tất cả tài liệu 
    public List<Document> list() {
        List<Document> documents = documentRepository.findAll();
        if (documents.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND");

        return documents;
    }

    // Xem chi tiết tài liệu 
    public Document get(long id) {
        return documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id));
    }

    // Chỉnh sửa tài liệu
    public String update(long id, UpdateDocumentRequest request) {
        Document document = documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id));
        
        Optional.of(request.title()).ifPresent(document::setTitle);
        Optional.of(request.author()).ifPresent(document::setAuthor);
        Optional.of(request.content()).ifPresent(document::setContent);
        documentRepository.save(document);

        return "Document updated successfully with ID: " + id + ".";
    }

    // Xóa tài liệu
    public String delete(long id) {        
        documentRepository.delete(documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id)));
        
        return "Document deleted successfully with ID: " + id + ".";
    }

    // Tìm danh sách tài liệu theo tên, tác giả và nội dung
    public List<Document> search(String searchString) {
        List<Document> documents = documentRepository.findAll();
        if (documents.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND");
        
        List<Document> searchList = list();
        for (Document document : documents) {
            if (document.getAuthor().contains(searchString) 
            || document.getTitle().contains(searchString)
            || document.getContent().contains(searchString)) {
                searchList.add(document);
            }
        } 
        if (searchList.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH SEARCH VALUE: " + searchString);

        return searchList;
    }
}
