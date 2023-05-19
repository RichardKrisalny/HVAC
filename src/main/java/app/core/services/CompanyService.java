package app.core.services;
import app.core.entities.Company;
import app.core.entities.Project;
import app.core.exeptions.ServiceException;
import org.springframework.stereotype.Service;


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
    public Project updateProject(Project project ,int companyId) throws ServiceException {
        if (!projectRepository.existsById(project.getId()))
            throw new ServiceException("the project "+project.getName()+" not found");
        userCredentialsRepository.save(project.getClient().getUserCredentials());
        addressRepository.save(project.getClient().getAddress());
        addressRepository.save(project.getAddress());
        clientRepository.save(project.getClient());
        Company company = companyRepository.findById(companyId).orElseThrow(()->new ServiceException("the company not found"));
        company.getProjects().removeIf((o -> o.getId()== project.getId()));
        company.addProject(project);
        return projectRepository.save(project);
    }
    public void deleteProject(int projectId,int companyId) throws ServiceException {
        if(!projectRepository.existsByIdAndCompanyId(projectId,companyId))
            throw new ServiceException("the company not found");
        projectRepository.deleteById(projectId);
    }
}
