package backend.model.request;

import java.util.Date;

import io.micrometer.common.lang.NonNull;

public record RegisterRequest(
                @NonNull String fullName,
                @NonNull String gender,
                @NonNull Date dateOfBirth,
                @NonNull String email,
                String phoneNumber,
                String address,
                @NonNull String username,
                @NonNull String password) {
}
