package app.core.repositories;

import app.core.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByUserCredentials_UserName(String userName);
    Optional<Company> findByUserCredentials_UserNameAndUserCredentials_Password(String userName, String password);

    boolean existsByEmail(String email);

    boolean existsByCompanyName(String companyName);

}