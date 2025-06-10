package backend.document.service;

import java.util.List;
import java.util.Objects;
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

        return "DOCUMENT CREATED SUCCESSFULLY WITH ID: " + document.getId();
    }

    // Xem danh sách tất cả tài liệu 
    public List<Document> list() {
        return documentRepository.findAll();
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

        return "DOCUMENT UPDATED SUCCESSFULLY WITH ID: " + id;
    }

    // Xóa tài liệu
    public String delete(long id) {        
        documentRepository.delete(documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id)));
        
        return "DOCUMENT DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Tìm danh sách tài liệu theo tên, tác giả và nội dung
    public List<Document> search(String searchString) {
        List<Document> documents = documentRepository.findAll();
        List<Document> searchList = list();
        for (Document document : documents) {
            if (Objects.toString(document.getAuthor(), "").contains(searchString) 
            || Objects.toString(document.getTitle(), "").contains(searchString)
            ||Objects.toString(document.getContent(), "").contains(searchString))  {
                searchList.add(document);
            }
        } 
        return searchList;
    }
}
