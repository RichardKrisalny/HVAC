package app.core.repositories;

import app.core.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    boolean existsByIdAndCompanyId(int id, int companyId);
    Optional<Project> findByNameAndCompanyId(String name, int companyId);
    Optional<Project> findByIdAndCompanyId(int id, int companyId);
    boolean existsByNameAndCompanyId(String name, int companyId);
    boolean existsByName(String name);
    List<Project> findByCompanyIdAndManager_Address_City(int companyId, String city);

    List<Project> findByCompanyId(int companyId);

    Optional<Project> findByName(String name);

    List<Project> findByStartDateBetweenAndManager_CompanyId(LocalDate startDateStart, LocalDate startDateEnd, int companyId);
    List<Project> findByEndDateBetweenAndManager_CompanyId(LocalDate startDateStart, LocalDate startDateEnd, int companyId);

    @Override
    boolean existsById(Integer integer);
}