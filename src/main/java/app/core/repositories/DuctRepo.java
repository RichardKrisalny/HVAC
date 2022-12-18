package app.core.repositories;

import app.core.entities.Duct;
import app.core.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuctRepo extends JpaRepository<Duct,Integer> {
}
