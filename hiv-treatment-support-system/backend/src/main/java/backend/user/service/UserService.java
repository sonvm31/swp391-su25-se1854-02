package backend.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Xem danh sách người dùng theo vai trò
    public List<User> listByRole(String role) {
        return userRepository.findUsersByRole(Role.valueOf(role.toUpperCase()));
    }

    // Xem chi tiết người dùng 
    public User get(int id) {
        return userRepository.findById(id).get();
    }

    // Chỉnh sửa người dùng 
    public String update(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id + "."));
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
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id + ".");
        }
        userRepository.deleteById(id);
        
        return "User deleted successfully with ID: " + id + ".";
    }
}
