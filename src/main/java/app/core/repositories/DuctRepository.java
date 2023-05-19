package app.core.repositories;

import app.core.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuctRepository extends JpaRepository<Duct, Integer> {
    List<Duct> findByProjectIdAndSteelThick(int projectId, double steelThick);

    List<Duct> findByProjectIdAndSteelType(int projectId, SteelType steelType);

    List<Duct> findByProjectIdAndConnectionType(int projectId, ConnectionType connectionType);

    List<Duct> findByProjectIdAndIsDone(int projectId, boolean isDone);
    List<Duct> findByProjectIdAndColor(int projectId, String color);

    List<Duct> findByProjectIdAndShape(int projectId, Shape shape);
}