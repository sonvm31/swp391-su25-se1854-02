package backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.Role;
import backend.model.User;
import backend.model.request.UpdateProfileRequest;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Admin lấy danh sách người dùng thông qua role
    public List<User> getUsersByRole(String role) {
        List<User> list = userRepository.findUsersByRole(Role.valueOf(role.toUpperCase()));
        return list;
    }

    // Admin yêu cầu thay đổi thông tin người dùng 
    public String updateUserById(int id, UpdateProfileRequest request) {
        User user = userRepository.findById(id).get();
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id + ".");
        }

        if (request.phoneNumber() != null) {
            user.setPhoneNumber(request.phoneNumber());
        }
        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }
        if (request.gender() != null) {
            user.setGender(request.gender());
        }
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.username() != null) {
            user.setUsername(request.username());
        }
        if (request.password() != null) {
            user.setPassword(request.password());
        }
        if (request.address() != null) {
            user.setAddress((request.address()));
        }
        if (request.dateOfBirth() != null) {
            user.setDateOfBirth(request.dateOfBirth());
        }
        userRepository.save(user);
        return "User updated successfully with ID: " + id + ".";
    }

    // Admin yêu cầu xóa người dùng 
    public String deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id + ".");
        }
        userRepository.deleteById(id);
        return "User deleted successfully with ID: " + id + ".";
    }

 
}
