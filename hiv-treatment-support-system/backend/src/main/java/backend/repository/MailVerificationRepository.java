package backend.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.model.MailVerification;

public interface MailVerificationRepository extends JpaRepository<MailVerification, Long> {
    Optional<MailVerification> findByToken(String token);

    void deleteByExpiryDateBefore(LocalDateTime dateTime);
}
