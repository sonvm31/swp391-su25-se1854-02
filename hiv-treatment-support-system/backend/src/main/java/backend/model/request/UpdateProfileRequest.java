package backend.model.request;

import java.util.Date;

public record UpdateProfileRequest(
        int id,
        String phoneNumber,
        String fullName,
        String gender,
        String email,
        String username,
        String password,
        String address,
        Date dateOfBirth) {
}
