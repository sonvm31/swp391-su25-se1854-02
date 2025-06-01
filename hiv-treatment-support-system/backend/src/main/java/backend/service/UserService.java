package backend.service;

import backend.model.Role;
import backend.model.User;
import backend.model.request.ListByRoleRequest;
import backend.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByRole(ListByRoleRequest request) {
        List<User> list = userRepository.findUsersByRole(Role.valueOf(request.role().toUpperCase()));
        return list;
    }
}
