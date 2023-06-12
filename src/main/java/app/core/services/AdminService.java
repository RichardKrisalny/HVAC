package app.core.services;

import app.core.entities.Company;
import app.core.entities.UserType;
import app.core.exeptions.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ServiceGlobal {
    public Company addCompany(Company company) throws ServiceException {
        if (!userCredentialsRepository.existsByUserName(company.getUserCredentials().getUserName())) {
            company.getUserCredentials().setUserType(UserType.COMPANY);
            userCredentialsRepository.save(company.getUserCredentials());
            addressRepository.save(company.getAddress());
            return companyRepository.save(company);
        } else
            throw new ServiceException("register failed - this user name already exists");
    }


    public void deleteCompany(int companyId) throws ServiceException {
        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
        }
    }

    public Company updateCompany(Company company) throws ServiceException {
        if (!companyRepository.existsById(company.getId())) {
            throw new ServiceException("the company " + company.getEmail() + " not Exists");
        }
        userCredentialsRepository.save(company.getUserCredentials());//BUG!!!!!!!!!!!!!!!!!!!
        addressRepository.save(company.getAddress());
        return companyRepository.save(company);
    }

    public Company getCompany(int companyID) throws ServiceException {
        return companyRepository.findById(companyID).orElseThrow(() -> new ServiceException("the company " + companyID + " not Exists"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
