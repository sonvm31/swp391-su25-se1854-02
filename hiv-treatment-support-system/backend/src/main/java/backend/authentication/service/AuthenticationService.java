package backend.authentication.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.authentication.dto.AuthenticationResponse;
import backend.authentication.dto.LoginRequest;
import backend.authentication.dto.RegisterRequest;
import backend.authentication.model.MailVerification;
import backend.authentication.reposiotry.MailVerificationRepository;
import backend.security.CustomUserDetails;
import backend.security.JwtService;
import backend.user.model.Role;
import backend.user.model.User;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final MailVerificationRepository mailVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;
    private final AuthenticationManager authenticationManager;


    // Đăng ký tài khoản và yêu cầu xác minh email
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) 
            throw new RuntimeException("Username already registered!");
        
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

        String token = UUID.randomUUID().toString();
        MailVerification verificationToken = MailVerification.builder()
            .token(token)
            .expiryDate(LocalDateTime.now().plusHours(24))
            .user(user)
            .build();
        mailVerificationRepository.save(verificationToken);

        String subject = "Verify your email";
        String verificationUrl = "http://localhost:8080/api/verify?token=" + token;
        String body = "Click the link to verify your email: " + verificationUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken, user.getUsername());
    }

    // Gửi thông báo trạng thái xác minh email 
    public String verify(String token) {
        Optional<MailVerification> mailVerification = mailVerificationRepository
                .findByToken(token);

        if (mailVerification.isEmpty() 
        || mailVerification.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return "Invalid or expired token.";
        }

        User user = mailVerification.get().getUser();
        user.setVerified(true);
        userRepository.save(user);

        return "Mail verified successfully.";
    }

    // Đăng nhập bằng tên tài khoản và mật khẩu
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        if (user.getAccountStatus().equals("UNACTIVE") 
        || !user.isVerified()) 
            throw new RuntimeException("Account is unactive or not verified yet");

        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);
        
        return new AuthenticationResponse(jwtToken, user.getUsername());
    }
}