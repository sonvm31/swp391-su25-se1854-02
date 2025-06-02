package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.Document;
import backend.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Optional<Document> getDocumentById(int id) {
        return documentRepository.findById(id);
    }
}
