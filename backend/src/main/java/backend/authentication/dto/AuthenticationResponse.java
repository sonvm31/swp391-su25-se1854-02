package backend.authentication.dto;

import io.micrometer.common.lang.NonNull;

public record AuthenticationResponse(
    @NonNull 
    String token,

    @NonNull 
    String name
) {
}
