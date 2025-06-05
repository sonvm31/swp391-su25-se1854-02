package backend.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.authentication.dto.AuthenticationResponse;
import backend.authentication.model.MailVerification;
import backend.authentication.reposiotry.MailVerificationRepository;
import backend.user.dto.CreateUserRequest;
import backend.user.dto.UpdateUserRequest;
import backend.user.model.Role;
import backend.user.model.User;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private MailVerificationRepository mailVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    
    // Tạo tài khoản và yêu cầu xác minh email
    public AuthenticationResponse create(CreateUserRequest request) {
        var user = User.builder()
            .username(request.username())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .accountStatus("ACTIVE")
            .role(Role.valueOf(request.role().toUpperCase()))
            .createdAt(LocalDateTime.now())
            .isVerified(false)
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

        return new AuthenticationResponse(token, user.getUsername());
    }
    
    // Xem chi tiết người dùng 
    public User get(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id + "."));
    }

    // Chỉnh sửa người dùng 
    public String update(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id + "."));
            
        Optional.ofNullable(request.phoneNumber()).ifPresent(user::setPhoneNumber);
        Optional.ofNullable(request.fullName()).ifPresent(user::setFullName);
        Optional.ofNullable(request.gender()).ifPresent(user::setGender);
        Optional.ofNullable(request.email()).ifPresent(user::setEmail);
        Optional.ofNullable(request.username()).ifPresent(user::setUsername);
        Optional.ofNullable(request.password()).ifPresent(user::setPassword);
        Optional.ofNullable(request.address()).ifPresent(user::setAddress);
        Optional.ofNullable(request.dateOfBirth()).ifPresent(user::setDateOfBirth);
        userRepository.save(user);

        return "User updated successfully with ID: " + id + ".";
    }

    // Xóa người dùng 
    public String delete(int id) {
        userRepository.delete(userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id + ".")));
        
        return "User deleted successfully with ID: " + id + ".";
    }

    // Xem danh sách người dùng theo vai trò
    public List<User> listByRole(String role) {
        List<User> users = userRepository.findUsersByRole(Role.valueOf(role.toUpperCase()));
        if (users.isEmpty()) 
            throw new RuntimeException("No user found.");
        
        return users;
    }

    // Xem danh sách người dùng theo tên
    public List<User> search(Role role, String searchString) {
        List<User> users = userRepository.findByFullNameContaining(searchString);
        if (users.isEmpty()) 
            throw new RuntimeException("No user found with full name: " + searchString + ".");

        return users;
    }

    // Tìm người dùng theo số điện thoại
    public User getByPhoneNumber(Role role, String searchString) {
        return userRepository.findByPhoneNumberContaining(searchString)
            .orElseThrow(() -> new RuntimeException("No user found with full name: " + searchString + "."));
    }

    // Tìm người dùng theo email
    public User getByEmail(Role role, String searchString) {
        return userRepository.findByEmailContaining(searchString)
            .orElseThrow(() -> new RuntimeException("No user found with email: " + searchString + "."));
    }

    // Tìm người dùng theo trạng thái xác minh email
    public User getByIsVerified(Role role, boolean isVerified) {
        return userRepository.findByIsVerified(isVerified)
            .orElseThrow(() -> new RuntimeException("No user found with email verified status: " + isVerified + "."));
    }

    // Tìm người dùng theo trạng thái tài khoản
    public User getByAccountStatus(Role role, String status) {
        return userRepository.findByAccountStatus(status)
            .orElseThrow(() -> new RuntimeException("No user found with full name: " + status + "."));
    }
}
