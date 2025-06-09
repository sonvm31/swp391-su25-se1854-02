package backend.user.dto;

import java.time.LocalDate;

public record UpdateUserRequest(
    String phoneNumber,

    String fullName,

    String gender,

    String email,

    String username,

    String password,

    String address,
    
    LocalDate dateOfBirth
) {
}
