package app.core.repositories;

import app.core.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByPasswordAndUserName(String password, String userName);

    boolean existsByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);
}