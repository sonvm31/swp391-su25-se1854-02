package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.User;
import backend.model.request.UpdateProfileRequest;
import backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserListController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsersByRole(@RequestParam String role) {
        List<User> response = userService.getUsersByRole(role);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@RequestBody UpdateProfileRequest request) {
        userService.updateUserById(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@RequestParam int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
