package backend.user.dto;

import io.micrometer.common.lang.NonNull;

public record CreateUserRequest(
    @NonNull 
    String email,

    @NonNull 
    String username,

    @NonNull 
    String password,

    @NonNull 
    String role
) {
}
