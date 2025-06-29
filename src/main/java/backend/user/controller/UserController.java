package backend.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.authentication.dto.AuthenticationResponse;
import backend.user.dto.CreateUserRequest;
import backend.user.dto.UpdateUserRequest;
import backend.user.model.Role;
import backend.user.model.User;
import backend.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(Map.of("message", userService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("message", userService.delete(id)));
    }

    @GetMapping("/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.listByRole(role));
    }

    @GetMapping("/{role}/search")
    public ResponseEntity<List<User>> search(@PathVariable Role role, @PathVariable String searchString) {
        return ResponseEntity.ok(userService.search(role, searchString));
    }

    @GetMapping("/{role}/phone-number/{phoneNumber}")
    public ResponseEntity<User> getByPhoneNumber(@PathVariable Role role, @PathVariable String searchString) {
        return ResponseEntity.ok(userService.getByPhoneNumber(role, searchString));
    }

    @GetMapping("/{role}/email-address/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable Role role, @PathVariable String searchString) {
        return ResponseEntity.ok(userService.getByEmail(role, searchString));
    }

    @GetMapping("/{role}/mail-verification-status/{isVerified}")
    public ResponseEntity<List<User>> getByPhoneNumber(@PathVariable Role role, @PathVariable boolean isVerified) {
        return ResponseEntity.ok(userService.getByIsVerified(role, isVerified));
    }

    @GetMapping("/{role}/account-status/{accountStatus}")
    public ResponseEntity<List<User>> getByAccountStatus(@PathVariable Role role, @PathVariable String accountStatus) {
        return ResponseEntity.ok(userService.getByAccountStatus(role, accountStatus));
    }
}
