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

    // Create document
    public String create(CreateDocumentRequest request) {
        var document = Document.builder()
            .title(request.title())
            .author(request.author())
            .content(request.content())
            .createdAt(request.createdAt())
            .build();
        documentRepository.save(document);

        return "DOCUMENT CREATED SUCCESSFULLY WITH ID: " + document.getId();
    }

    // List documents
    public List<Document> list() {
        return documentRepository.findAll();
    }

    // Read document detail
    public Document get(long id) {
        return documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id));
    }

    // Update document
    public String update(long id, UpdateDocumentRequest request) {
        Document document = documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id));
        
        Optional.ofNullable(request.title()).ifPresent(document::setTitle);
        Optional.ofNullable(request.author()).ifPresent(document::setAuthor);
        Optional.ofNullable(request.content()).ifPresent(document::setContent);
        documentRepository.save(document);

        return "DOCUMENT UPDATED SUCCESSFULLY WITH ID: " + id;
    }

    // Delete document
    public String delete(long id) {        
        documentRepository.delete(documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCUMENT FOUND WITH ID: " + id)));
        
        return "DOCUMENT DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Search documents by title, author and content
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
