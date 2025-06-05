package backend.authentication.model;

import java.time.LocalDateTime;

import backend.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mail_verification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;
    
    private LocalDateTime expiryDate;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
