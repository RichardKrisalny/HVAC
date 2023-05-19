package app.core.repositories;

import app.core.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SquareDuctRepository extends JpaRepository<SquareDuct, Integer> {
    List<SquareDuct> findByProjectIdAndSteelThick(int projectId, double steelThick);

    List<SquareDuct> findByProjectIdAndSteelType(int projectId, SteelType steelType);

    List<SquareDuct> findByProjectIdAndConnectionType(int projectId, ConnectionType connectionType);

    List<SquareDuct> findByProjectIdAndIsDone(int projectId, boolean isDone);
    List<SquareDuct> findByProjectIdAndColor(int projectId, String color);

    List<SquareDuct> findByProjectIdAndShape(int projectId, Shape shape);
}