package backend.authentication.dto;

import io.micrometer.common.lang.NonNull;

public record LoginRequest(
    @NonNull 
    String username,

    @NonNull 
    String password
    ) {
}
