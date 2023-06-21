package app.core.services;

import app.core.entities.Employee;
import app.core.entities.Project;
import app.core.jwt.User;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService extends ServiceGlobal {
    public List<Project> getProject(int employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ServiceException("the employee not exists")).getProjects();
    }

    public Employee editEmployee(Employee employee, int employeeId) {
        employee.setId(employeeId);
        userCredentialsRepository.save(employee.getUserCredentials());
        addressRepository.save(employee.getAddress());
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(User user) {
        return employeeRepository.findByUserCredentials_UserName(user.getUserName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the employee not found"));
    }

}
