package app.core.repositories;

import app.core.entities.CircularDuct;
import app.core.entities.ConnectionType;
import app.core.entities.Shape;
import app.core.entities.SteelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircularDuctRepository extends JpaRepository<CircularDuct, Integer> {
    List<CircularDuct> findByProjectIdAndSteelThick(int projectId, double steelThick);

    List<CircularDuct> findByProjectIdAndSteelType(int projectId, SteelType steelType);

    List<CircularDuct> findByProjectIdAndConnectionType(int projectId, ConnectionType connectionType);

    List<CircularDuct> findByProjectIdAndIsDone(int projectId, boolean isDone);
    List<CircularDuct> findByProjectIdAndColor(int projectId, String color);

    List<CircularDuct> findByProjectIdAndShape(int projectId, Shape shape);
}