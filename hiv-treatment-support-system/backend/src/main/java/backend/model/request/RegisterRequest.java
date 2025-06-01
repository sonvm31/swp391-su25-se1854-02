package backend.model.request;

import java.util.Date;

public record RegisterRequest(String fullName, String gender, Date dateOfBirth, String email, int phoneNumber,
                String address, String username, String password) {
}
