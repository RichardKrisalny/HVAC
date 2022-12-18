package app.core.repositories;

import app.core.entities.Project;
import app.core.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Integer> {
}
