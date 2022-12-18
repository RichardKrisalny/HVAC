package app.core.repositories;

import app.core.entities.User;
import app.core.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepo extends JpaRepository<Worker,Integer> {
}
