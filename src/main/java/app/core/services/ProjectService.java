package app.core.services;


import app.core.entities.*;
import app.core.exeptions.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService extends ServiceGlobal {
    public Employee setManager(int employeeId, int projectId) throws ServiceException {
        Employee manager = employeeRepository.findById(employeeId).orElseThrow(() -> new ServiceException("the Employee " + employeeId + " not found"));
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).setManager(manager);
        return manager;
    }

    public Employee addEmployee(Employee employee, int projectId) throws ServiceException {
        if (!employeeRepository.existsById(employee.getId()))
            throw new ServiceException("the Employee " + employee.getId() + " not found");
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found"));
        if (project.getCompanyId() != employee.getCompanyId())
            throw new ServiceException("the Employee work in other company");
        project.addEmployee(employee);
        return employee;
    }

    public Duct addDuct(Duct duct, int projectId) throws ServiceException {
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).addDuct(duct);
        return ductRepository.save(duct);
    }

    public void deleteDuct(int ductId, int projectId) throws ServiceException {
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).getDucts().removeIf((d) -> d.getId() == ductId);
    }

    public Duct updateDuct(Duct duct, int projectId) throws ServiceException {
        ductRepository.findByProjectIdAndId(projectId, duct.getId()).orElseThrow(() -> new ServiceException("the duct " + duct.getId() + " not found"));
        return ductRepository.save(duct);
    }

    public Duct getDuct(int ductId, int projectId) throws ServiceException {
        return ductRepository.findByProjectIdAndId(projectId, ductId).orElseThrow(() -> new ServiceException("the duct " + ductId + " not found"));
    }

    public List<Duct> getDuctsByColor(int projectId, String color) {
        return ductRepository.findByProjectIdAndColor(projectId, color);
    }

    public List<Duct> getAllDuctsByConnectionType(int projectId, ConnectionType connectionType) {
        return ductRepository.findByProjectIdAndConnectionType(projectId, connectionType);
    }

    public List<Duct> getAllDucts(int projectId) {
        return ductRepository.findByProjectId(projectId);
    }

    public List<Duct> getAllDoneDucts(int projectId, boolean isDone) {
        return ductRepository.findByProjectIdAndIsDone(projectId, isDone);
    }

    public List<Duct> getDuctsByShape(int projectId, Shape shape) {
        return ductRepository.findByProjectIdAndShape(projectId, shape);
    }

    public List<Duct> getAllDuctsByThick(int projectId, double thick) {
        return ductRepository.findByProjectIdAndSteelThick(projectId, thick);
    }

    public List<Duct> getAllDuctsBySteelType(int projectId, SteelType steelType) {
        return ductRepository.findByProjectIdAndSteelType(projectId, steelType);
    }

    public String addBlueprint(String name, String file, int projectId) throws ServiceException {
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).getBlueprints().put(name, file);
        return name;
    }

    public void deleteBlueprint(String name, int projectId) throws ServiceException {
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).getBlueprints().remove(name);
    }
}
