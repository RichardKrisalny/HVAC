package app.core.repositories;

import app.core.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByUserCredentials_UserName(String userName);

    Optional<Client> findByUserCredentials_UserNameAndUserCredentials_Password(String userName, String password);

    boolean existsByUserCredentials_UserName(String userName);

    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);
}