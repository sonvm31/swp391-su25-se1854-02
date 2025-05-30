package backend.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private String status;
    @Enumerated(EnumType.STRING)
    private Role role;
}
