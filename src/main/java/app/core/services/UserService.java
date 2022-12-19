package app.core.services;

import app.core.entities.*;
import app.core.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private DuctRepo ductRepo;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private MeasurementRepo measurementRepo;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WorkerRepo workerRepo;
    private int userId;

    public boolean login(String login, String password) {
        if(userRepo.findByLoginAndPassword(login,password)!=null) {
            userId = userRepo.findByLoginAndPassword(login, password).getId();
            return true;
        }
        else
            throw new RuntimeException("user not found");
    }


    public Contact addContact(Contact contact) {
        projectRepo.findById(contact.getProjectId()).orElseThrow(RuntimeException::new).setContact(contact);
        return contactRepo.save(contact);
    }

    public Worker addWorker(Worker worker) {
        return workerRepo.save(worker);
    }

    public Project addProject(Project project) {
        userRepo.findById(userId).orElseThrow(RuntimeException::new).addProject(project);
        return projectRepo.save(project);
    }

    public Duct addDuct(Duct duct,int projectId){
        projectRepo.findById(projectId).orElseThrow(RuntimeException::new).addDuct(duct);
        return ductRepo.save(duct);
    }

    public Measurement addMeasurement(Measurement measurement,int ductId){
        ductRepo.findById(ductId).orElseThrow(RuntimeException::new).setMeasurement(measurement);
       return measurementRepo.save(measurement);
    }

    public Contact updateContact(Contact contact) {
        contactRepo.findById(contact.getId()).orElseThrow(RuntimeException::new);
        return contactRepo.save(contact);
    }

    public Worker updateWorker(Worker worker) {
        workerRepo.findById(worker.getId()).orElseThrow(RuntimeException::new);
        return workerRepo.save(worker);
    }

    public Project updateProject(Project project) {
        projectRepo.findById(project.getId()).orElseThrow(RuntimeException::new);
        return projectRepo.save(project);
    }

    public Duct updateDuct(Duct duct){
        ductRepo.findById(duct.getId()).orElseThrow(RuntimeException::new);
        return ductRepo.save(duct);
    }

    public Measurement updateMeasurement(Measurement measurement){
    measurementRepo.findById(measurement.getId()).orElseThrow(RuntimeException::new);
    return measurementRepo.save(measurement);
    }

    public void deleteContact (int contactId) {
        contactRepo.delete(contactRepo.findById(contactId).orElseThrow(RuntimeException::new));
    }

    public void deleteWorker(int workerId) {
        workerRepo.delete(workerRepo.findById(workerId).orElseThrow(RuntimeException::new));
    }

    public void deleteProject(int projectId) {
        projectRepo.delete(projectRepo.findById(projectId).orElseThrow(RuntimeException::new));
    }

    public void deleteDuct(int ductId){
        ductRepo.delete(ductRepo.findById(ductId).orElseThrow(RuntimeException::new));
    }

    public void deleteMeasurement(int measurementId){
        measurementRepo.delete(measurementRepo.findById(measurementId).orElseThrow(RuntimeException::new));
    }
    public void addWorkerToProject(int workerId,int projectId){
        Project project=projectRepo.findById(projectId).orElseThrow(RuntimeException::new);
        Worker worker=workerRepo.findById(workerId).orElseThrow(RuntimeException::new);
        project.addWorker(worker);
    }
}