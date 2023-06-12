package app.core.controllers;

import app.core.entities.Company;
import app.core.entities.UserCredentials;
import app.core.exeptions.ServiceException;
import app.core.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.message.AuthException;

@CrossOrigin
@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody Company company) throws ServiceException {
        try {
            return authService.register(company);
        } catch (JsonProcessingException | AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials userCredentials) {
        try {
            return authService.Login(userCredentials.getUserName(), userCredentials.getPassword());
        } catch (AuthException | JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
