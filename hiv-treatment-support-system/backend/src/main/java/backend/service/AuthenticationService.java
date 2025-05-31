package backend.service;

import backend.dto.AuthenticationResponse;
import backend.dto.RegisterRequest;
import backend.model.User;
import backend.repository.UserRepository;
import backend.config.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
                .roleID(0)
                .build();
        userRepository.save(user);

        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken, user.getUsername());
    }

    public AuthenticationResponse login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        UserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken, user.getUsername());
    }
}