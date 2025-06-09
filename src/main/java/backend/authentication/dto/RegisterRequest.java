package backend.authentication.dto;

import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;

public record RegisterRequest(
    @NonNull 
    String fullName,

    @NonNull 
    String gender,

    @NonNull 
    LocalDate dateOfBirth,

    @NonNull 
    String email,

    @NonNull 
    String username,

    @NonNull 
    String password,

    String phoneNumber,
    
    String address
) {
}
