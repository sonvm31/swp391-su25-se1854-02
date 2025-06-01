package backend.controller;

import backend.service.AuthenticationService;
import backend.model.request.CreateAccountRequest;
import backend.model.request.LoginRequest;
import backend.model.request.MailVerificationRequest;
import backend.model.request.RegisterRequest;
import backend.model.response.AuthenticationResponse;
import backend.model.response.MailVerificationResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

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
    public ResponseEntity<MailVerificationResponse> verifyEmail(@RequestBody MailVerificationRequest request) {
        MailVerificationResponse response = authenticationService.verify(request);
        return ResponseEntity.ok(response);
    }
}