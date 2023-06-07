package app.core.services;


import app.core.entities.*;
import app.core.exeptions.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService extends ServiceGlobal {
    public Employee setManager(int employeeId, int projectId, int companyId) throws ServiceException {
        Employee manager = employeeRepository.findById(employeeId).orElseThrow(() -> new ServiceException("the Employee " + employeeId + " not found"));
        projectRepository.findById(projectId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found")).setManager(manager);
        return manager;
    }

    public Employee addEmployee(int employeeId, int projectId, int companyId) throws ServiceException {
        Project project = getProject(projectId, companyId);
        Employee employee = getEmployee(employeeId, companyId);
        if (project.getCompanyId() != employee.getCompanyId())
            throw new ServiceException("the Employee work in other company");
        project.addEmployee(employee);
        projectRepository.save(project);
//        System.out.println(getEmployee(employeeId, companyId).getProjects());
//        System.out.println(getProject(projectId,companyId).getEmployees());
        return employee;
    }

    public void removeEmployee(int employeeId, int projectId, int companyId) throws ServiceException {
        if (!employeeRepository.existsById(employeeId))
            throw new ServiceException("the Employee " + employeeId + " not found");
        getProject(projectId, companyId).getEmployees().removeIf((e) -> e.getId() == employeeId);

    }

    public Duct addDuct(Duct duct, int projectId, int companyId) throws ServiceException {
        getProject(projectId, companyId).addDuct(duct);
        return ductRepository.save(duct);
    }

    public void deleteDuct(int ductId, int projectId, int companyId) throws ServiceException {
        getProject(projectId, companyId).getDucts().removeIf((d) -> d.getId() == ductId);
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

    public String addBlueprint(String name, String file, int projectId, int companyId) throws ServiceException {
        getProject(projectId, companyId).getBlueprints().put(name, file);
        return name;
    }

    public void deleteBlueprint(String name, int projectId, int companyId) throws ServiceException {
        getProject(projectId, companyId).getBlueprints().remove(name);
    }

    public Project getProject(int projectId, int companyId) throws ServiceException {
        return projectRepository.findByIdAndCompanyId(projectId, companyId).orElseThrow(() -> new ServiceException("the project " + projectId + " not found"));
    }

    public Employee getEmployee(int employeeId, int companyId) throws ServiceException {
        return employeeRepository.findByIdAndCompanyId(employeeId, companyId).orElseThrow(() -> new ServiceException("the employee " + employeeId + " not found"));
    }
}
