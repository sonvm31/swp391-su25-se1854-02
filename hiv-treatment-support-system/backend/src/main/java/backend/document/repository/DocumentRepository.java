package backend.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.document.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
