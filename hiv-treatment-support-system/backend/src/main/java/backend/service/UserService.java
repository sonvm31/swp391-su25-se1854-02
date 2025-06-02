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

    public List<User> getUsersByRole(String role) {
        List<User> list = userRepository.findUsersByRole(Role.valueOf(role.toUpperCase()));
        return list;
    }

    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public boolean updateUserById(int id, UpdateProfileRequest request) {
        User user = userRepository.findById(id).get();
        if (user != null) {
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
            return true;
        }
    return false;
    }
}
