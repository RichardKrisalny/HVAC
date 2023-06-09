package app.core.controllers;

import app.core.entities.*;
import app.core.exeptions.ServiceException;
import app.core.jwt.User;
import app.core.services.CompanyService;
import app.core.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProjectService projectService;


    @PostMapping(value = "/addProject", headers = {HttpHeaders.AUTHORIZATION})
    public Project addProject(@RequestBody Project project, HttpServletRequest req) {
        try {
            return companyService.addProject(project, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/getProject", headers = {HttpHeaders.AUTHORIZATION})
    public Project getProject(int projectId, HttpServletRequest req) {
        try {
            return companyService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllProjects", headers = {HttpHeaders.AUTHORIZATION})
    public List<Project> getAllProjects(HttpServletRequest req) {

        return companyService.getAllProjects(companyService.getCompany((User) req.getAttribute("user")).getId());
    }

    @GetMapping(value = "/getProjectByName", headers = {HttpHeaders.AUTHORIZATION})
    public Project getProjectByName(String projectName, HttpServletRequest req) {
        try {
            return companyService.getProjectByName(projectName, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/updateProject", headers = {HttpHeaders.AUTHORIZATION})
    public Project updateProject(@RequestBody Project project, @RequestParam int projectId, HttpServletRequest req) {
        try {
            return companyService.updateProject(project, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteProject", headers = {HttpHeaders.AUTHORIZATION})
    public void deleteProject(@RequestBody int projectId, HttpServletRequest req) {

        try {
            companyService.deleteProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addEmployee", headers = {HttpHeaders.AUTHORIZATION})
    public Employee addEmployee(@RequestBody Employee employee, HttpServletRequest req) {

        try {
            return companyService.addEmployee(employee, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/getEmployee", headers = {HttpHeaders.AUTHORIZATION})
    public Employee getEmployee(int employeeId, HttpServletRequest req) {

        try {
            return companyService.getEmployee(employeeId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getEmployeeByTz", headers = {HttpHeaders.AUTHORIZATION})
    public Employee getEmployee(String employeeTZ, HttpServletRequest req) {

        try {
            return companyService.getEmployee(employeeTZ, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllEmployees", headers = {HttpHeaders.AUTHORIZATION})
    public List<Employee> getAllEmployees(HttpServletRequest req) {

        return companyService.getAllEmployees(companyService.getCompany((User) req.getAttribute("user")).getId());
    }

    @GetMapping(value = "/getAllEmployeesInProject", headers = {HttpHeaders.AUTHORIZATION})
    public List<Employee> getAllEmployees(int projectId, HttpServletRequest req) {

        return companyService.getAllEmployees(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
    }

    @DeleteMapping(value = "/deleteEmployees", headers = {HttpHeaders.AUTHORIZATION})
    public void deleteEmployee(int employeeId, HttpServletRequest req) {
        try {
            companyService.deleteEmployee(employeeId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addEmployeeToProject", headers = {HttpHeaders.AUTHORIZATION})
    public Employee addEmployeeToProject(int employeeId, int projectId, HttpServletRequest req) {
        try {
            return projectService.addEmployee(employeeId, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(value = "/removeEmployeeFromProject", headers = {HttpHeaders.AUTHORIZATION})
    public void removeEmployeeFromProject(int employeeId, int projectId, HttpServletRequest req) {
        try {
            projectService.removeEmployee(employeeId, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addSquareDuct", headers = {HttpHeaders.AUTHORIZATION})
    public Duct addDuct(SquareDuct duct, int projectId, HttpServletRequest req) {
        try {
            return projectService.addDuct(duct, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/addCircularDuct", headers = {HttpHeaders.AUTHORIZATION})
    public Duct addDuct(CircularDuct duct, int projectId, HttpServletRequest req) {
        try {
            return projectService.addDuct(duct, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteDuct", headers = {HttpHeaders.AUTHORIZATION})
    public void deleteDuct(int ductId, int projectId, HttpServletRequest req) {
        try {
            projectService.deleteDuct(ductId, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(value = "/updateDuct", headers = {HttpHeaders.AUTHORIZATION})
    public Duct updateDuct(int ductId, Duct duct, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.updateDuct(ductId, duct, projectId);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getDuct", headers = {HttpHeaders.AUTHORIZATION})
    public Duct getDuct(int ductId, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getDuct(ductId, projectId);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getDuctByColor", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getDuctByColor(String color, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getDuctsByColor(projectId, color);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllDuctsByConnectionType", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getAllDuctsByConnectionType(ConnectionType connectionType, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getAllDuctsByConnectionType(projectId, connectionType);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllDucts", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getAllDucts(int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getAllDucts(projectId);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllDoneDucts", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getAllDoneDucts(boolean isDone, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getAllDoneDucts(projectId, isDone);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getDuctsByShape", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getDuctsByShape(Shape shape, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getDuctsByShape(projectId, shape);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllDuctsByThick", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getAllDuctsByThick(double thick, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getAllDuctsByThick(projectId, thick);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/getAllDuctsBySteelType", headers = {HttpHeaders.AUTHORIZATION})
    public List<Duct> getAllDuctsBySteelType(SteelType steelType, int projectId, HttpServletRequest req) {
        try {
            projectService.getProject(projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
            return projectService.getAllDuctsBySteelType(projectId, steelType);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addBlueprint", headers = {HttpHeaders.AUTHORIZATION})
    public String addBlueprint(String name, String file, int projectId, HttpServletRequest req) {
        try {
            return projectService.addBlueprint(name, file, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBlueprint", headers = {HttpHeaders.AUTHORIZATION})
    public void deleteBlueprint(String name, int projectId, HttpServletRequest req) {
        try {
            projectService.deleteBlueprint(name, projectId, companyService.getCompany((User) req.getAttribute("user")).getId());
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}




