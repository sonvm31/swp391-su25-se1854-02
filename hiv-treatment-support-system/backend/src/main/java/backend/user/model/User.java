package backend.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phoneNumber;
    private String fullName;
    private String gender;
    private String email;
    private String username;
    private String password;
    private String accountStatus;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private boolean isVerified;
    private Role role;
    
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MailVerification mailVerification;

    public String getDisplayId() {
        return role.name().substring(0, 3) + "-" + id;
    }
}
