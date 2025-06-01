package backend.repository;

import backend.model.Role;
import backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    Optional<User> findUserByUsername(String username);

    List<User> findUsersByRole(Role role);
}