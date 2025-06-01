package backend.model.request;

public record CreateAccountRequest(String email, String username, String password, String role) {
}
