package backend.model;

import jakarta.persistence.*;
import lombok.*;

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
