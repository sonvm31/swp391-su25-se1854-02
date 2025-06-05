package backend.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.user.model.Role;
import backend.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findUsersByRole(Role role);

    List<User> findByFullNameContaining(String searchString);

    Optional<User> findByPhoneNumberContaining(String searchString);

    Optional<User> findByEmailContaining(String searchString);

    Optional<User> findByIsVerified(boolean isVerified);

    Optional<User> findByAccountStatus(String status);
}
