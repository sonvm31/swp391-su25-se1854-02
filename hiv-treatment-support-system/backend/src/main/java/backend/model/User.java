package backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private int phoneNumber;
    private String fullName;
    private String gender;
    private String email;
    private String username;
    private String password;
    private String accountStatus;
    private String address;
    private Date dateOfBirth;
    private Date createdAt;
    private int roleID;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleID")
    private Role role;
}
