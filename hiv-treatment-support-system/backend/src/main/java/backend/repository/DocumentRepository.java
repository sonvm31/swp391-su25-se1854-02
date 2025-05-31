package backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
