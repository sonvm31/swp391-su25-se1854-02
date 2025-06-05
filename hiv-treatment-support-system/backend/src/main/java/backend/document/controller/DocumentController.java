package backend.document.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateDocumentRequest request) {
        return ResponseEntity.ok(Map.of("message", documentService.create(request)));
    }

    @GetMapping()
    public ResponseEntity<List<Document>> list() {
        return ResponseEntity.ok(documentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> get(@PathVariable int id) {
        return ResponseEntity.ok(documentService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable int id, @RequestBody UpdateDocumentRequest request) {
        return ResponseEntity.ok(Map.of("message", documentService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("message", documentService.delete(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam String searchString) {
        return ResponseEntity.ok(documentService.search(searchString));
    }
}
