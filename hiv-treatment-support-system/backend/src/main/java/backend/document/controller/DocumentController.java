package backend.document.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.document.dto.CreateDocumentRequest;
import backend.document.dto.UpdateDocumentRequest;
import backend.document.model.Document;
import backend.document.service.DocumentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateDocumentRequest request) {
        String response = documentService.create(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Document>> list() {
        List<Document> response = documentService.list();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> get(@PathVariable int id) {
        Document response = documentService.get(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateDocumentRequest request) {
        String response = documentService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = documentService.delete(id);
        return ResponseEntity.ok(response);
    }
}
