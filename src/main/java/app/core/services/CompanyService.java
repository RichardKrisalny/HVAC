package app.core.services;
import app.core.entities.Company;
import app.core.entities.Employee;
import app.core.entities.Project;
import app.core.exeptions.ServiceException;
import app.core.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService extends ServiceGlobal {
    public Project addProject(Project project,int companyId) throws ServiceException {
        if (projectRepository.existsByNameAndCompanyId(project.getName(), companyId))
            throw new ServiceException("the company all ready have project with this name---"+project.getName());
        userCredentialsRepository.save(project.getClient().getUserCredentials());
        addressRepository.save(project.getClient().getAddress());
        addressRepository.save(project.getAddress());
        clientRepository.save(project.getClient());
        companyRepository.findById(companyId).orElseThrow(()->new ServiceException("the company not found")).addProject(project);
        return projectRepository.save(project);
    }
    public Project getProject(int projectId ,int companyId) throws ServiceException {
        return projectRepository.findByIdAndCompanyId(projectId,companyId).orElseThrow(()->new ServiceException("the project not found"));
    }
    public Project getProjectByName(String projectName ,int companyId) throws ServiceException {
        return projectRepository.findByNameAndCompanyId(projectName,companyId).orElseThrow(()->new ServiceException("the project not found"));
    }
    public List<Project> getAllProjects(int companyId)  {
        return projectRepository.findByCompanyId(companyId);
    }
    public Project updateProject(Project project,int projectId ,int companyId) throws ServiceException {
        if (!projectRepository.existsByIdAndCompanyId(projectId,companyId))
            throw new ServiceException("the project "+project.getName()+" not found");
        userCredentialsRepository.save(project.getClient().getUserCredentials());
        addressRepository.save(project.getClient().getAddress());
        addressRepository.save(project.getAddress());
        clientRepository.save(project.getClient());
        project.setId(projectId);
        return projectRepository.save(project);
    }
    public void deleteProject(int projectId,int companyId) throws ServiceException {
        if(!projectRepository.existsByIdAndCompanyId(projectId,companyId))
            throw new ServiceException("the company not found");
        projectRepository.deleteById(projectId);
    }
    public Employee addEmployee(Employee employee,int companyId) throws ServiceException {
        if(employeeRepository.existsByTZAndCompanyId(employee.getTZ(), companyId))
            throw new ServiceException("the employee already exists");
        userCredentialsRepository.save(employee.getUserCredentials());
        addressRepository.save(employee.getAddress());
        companyRepository.findById(companyId).orElseThrow(()->new ServiceException("the company not found")).addEmployee(employee);
        return employeeRepository.save(employee);
        }
}
