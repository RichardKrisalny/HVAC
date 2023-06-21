package app.core.services;

import app.core.entities.Client;
import app.core.entities.Project;
import app.core.jwt.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService extends ServiceGlobal {
    public List<Project> getAllProjects(int clientId) {
        return projectRepository.findByClient_Id(clientId);
    }

    public Client editClient(Client client, int clientId) {
        client.setId(clientId);
        userCredentialsRepository.save(client.getUserCredentials());
        addressRepository.save(client.getAddress());
        return clientRepository.save(client);
    }

    public Client getClient(User user) {
        return clientRepository.findByUserCredentials_UserName(user.getUserName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the employee not found"));
    }
}
