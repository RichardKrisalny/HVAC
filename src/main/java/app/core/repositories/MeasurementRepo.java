package app.core.repositories;

import app.core.entities.Measurement;
import app.core.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepo extends JpaRepository<Measurement,Integer> {
}
