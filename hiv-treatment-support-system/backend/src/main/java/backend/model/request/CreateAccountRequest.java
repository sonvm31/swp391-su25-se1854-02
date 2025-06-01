package backend.model.request;

import io.micrometer.common.lang.NonNull;

public record CreateAccountRequest(
        @NonNull String email,
        @NonNull String username,
        @NonNull String password,
        @NonNull String role) {
}
