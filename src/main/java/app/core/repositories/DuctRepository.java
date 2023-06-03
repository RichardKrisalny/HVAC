package app.core.repositories;

import app.core.entities.ConnectionType;
import app.core.entities.Duct;
import app.core.entities.Shape;
import app.core.entities.SteelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DuctRepository extends JpaRepository<Duct, Integer> {
    List<Duct> findByProjectId(int projectId);

    Optional<Duct> findByProjectIdAndId(int projectId, int id);

    List<Duct> findByProjectIdAndSteelThick(int projectId, double steelThick);

    List<Duct> findByProjectIdAndSteelType(int projectId, SteelType steelType);

    List<Duct> findByProjectIdAndConnectionType(int projectId, ConnectionType connectionType);

    List<Duct> findByProjectIdAndIsDone(int projectId, boolean isDone);

    List<Duct> findByProjectIdAndColor(int projectId, String color);

    List<Duct> findByProjectIdAndShape(int projectId, Shape shape);
}