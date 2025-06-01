package backend.controller;

import backend.service.AuthenticationService;
import backend.model.MailVerification;
import backend.model.User;
import backend.model.request.CreateAccountRequest;
import backend.model.request.LoginRequest;
import backend.model.request.RegisterRequest;
import backend.model.response.AuthenticationResponse;
import backend.repository.MailVerificationRepository;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final MailVerificationRepository mailVerificationRepository;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> create(@RequestBody CreateAccountRequest request) {
        AuthenticationResponse response = authenticationService.createAccount(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        Optional<MailVerification> mailVerification = mailVerificationRepository.findMailVerificationByToken(token);

        if (mailVerification.isEmpty() || mailVerification.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
        User user = mailVerification.get().getUser();
        user.setVerified(true);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully!");
    }
}