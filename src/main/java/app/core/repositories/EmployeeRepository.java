package app.core.repositories;

import app.core.entities.Employee;
import app.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserCredentials_UserName(String userName);

    boolean existsByIdAndCompanyId(int id, int companyId);

    List<Employee> findByProjects_IdAndCompanyId(int id, int companyId);

    Optional<Employee> findByTZAndCompanyId(String TZ, int companyId);

    Optional<Employee> findByIdAndCompanyId(int id, int companyId);

    boolean existsByTZAndCompanyId(String TZ, int companyId);

    List<Employee> findByCompanyId(int companyId);

    Optional<Employee> findByName(String name);

    Optional<Employee> findByUserCredentials_UserNameAndUserCredentials_Password(String userName, String password);

    List<Employee> findByRoleAndCompanyId(Role role, int companyId);

    Optional<Employee> findByTZ(String TZ);

    Optional<Employee> findByPhone(String phone);

    List<Employee> findByAddress_City(String city);

    boolean existsByEmail(String email);
}