package backend.service;

import backend.model.MailVerification;
import backend.model.Role;
import backend.model.User;
import backend.model.request.CreateAccountRequest;
import backend.model.request.LoginRequest;
import backend.model.request.MailVerificationRequest;
import backend.model.request.RegisterRequest;
import backend.model.response.AuthenticationResponse;
import backend.model.response.MailVerificationResponse;
import backend.repository.MailVerificationRepository;
import backend.repository.UserRepository;
import backend.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final MailVerificationRepository mailVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;
    private final AuthenticationManager authenticationManager;

    // Patient register
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findUserByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already registered!");
        }

        var user = User.builder()
                .fullName(request.fullName())
                .gender(request.gender())
                .dateOfBirth(request.dateOfBirth())
                .email(request.email())
                .address(request.address())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .accountStatus("ACTIVE")
                .role(Role.PATIENT)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken, user.getUsername());
    }

    // Check login
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.findUserByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        if (user.getAccountStatus().equals("UNACTIVE") || !user.isVerified()) {
            throw new RuntimeException("Account is unactive or not verified yet");
        }
        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken, user.getUsername());
    }

    // Admin create account
    public AuthenticationResponse createAccount(CreateAccountRequest request) {
        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .accountStatus("ACTIVE")
                .role(Role.valueOf(request.role().toUpperCase()))
                .createdAt(LocalDateTime.now())
                .isVerified(false)
                .build();

        String token = UUID.randomUUID().toString();
        MailVerification verificationToken = MailVerification.builder()
                .token(token)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .user(user)
                .build();
        userRepository.save(user);
        mailVerificationRepository.save(verificationToken);
        String subject = "Verify your email";
        String verificationUrl = "http://localhost:8080/api/verify?token=" + token;
        String body = "Click the link to verify your email: " + verificationUrl;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        return new AuthenticationResponse(token, user.getUsername());
    }

    public MailVerificationResponse verify(MailVerificationRequest request) {
        Optional<MailVerification> mailVerification = mailVerificationRepository
                .findMailVerificationByToken(request.token());
        if (mailVerification.isEmpty() || mailVerification.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return new MailVerificationResponse("Invalid or expired token");
        }
        User user = mailVerification.get().getUser();
        user.setVerified(true);
        userRepository.save(user);
        return new MailVerificationResponse("Validate success");
    }
}