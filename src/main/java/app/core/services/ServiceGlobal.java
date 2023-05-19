package app.core.services;

import app.core.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceGlobal {
    @Autowired
    protected AddressRepository addressRepository;
    @Autowired
    protected CircularDuctRepository circularDuctRepository;
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected DuctRepository ductRepository;
    @Autowired
    protected SquareDuctRepository squareDuctRepository;
    @Autowired
    protected UserCredentialsRepository userCredentialsRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected ProjectRepository projectRepository;
    @Autowired
    protected CompanyRepository companyRepository;
}
