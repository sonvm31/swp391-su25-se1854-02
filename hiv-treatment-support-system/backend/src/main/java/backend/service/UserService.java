package backend.service;

import backend.model.User;
import backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getRoleNameForUser(int userId) {
        User user = userRepository.findUserById(userId);
        if (user != null && user.getRole() != null) {
            return user.getRole().getRoleName();
        }
        return null;
    }
}
