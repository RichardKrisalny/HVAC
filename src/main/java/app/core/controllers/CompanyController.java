package app.core.controllers;

import app.core.entities.Employee;
import app.core.entities.Project;
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

}




