package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
