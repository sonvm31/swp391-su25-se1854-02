package backend.documentimage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.documentimage.model.DocumentImage;

public interface DocumentImageRepository extends JpaRepository<DocumentImage, Long> {
    List<DocumentImage> findByDocumentId(long id);
}
