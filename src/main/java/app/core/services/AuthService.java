package app.core.services;

import app.core.entities.Company;
import app.core.entities.UserCredentials;
import app.core.entities.UserType;
import app.core.jwt.JwtUser;
import app.core.jwt.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.Objects;

@Service
public class AuthService extends ServiceGlobal {
    @Autowired
    private JwtUser jwtUser;
    @Autowired
    private User user;

    public String Login(String userName, String password) throws AuthException, JsonProcessingException {
        System.out.println(userName + "  " + password);
        if (Objects.equals(userName, "admin") && Objects.equals(password, "admin")) {
            user.setUserName("admin");
            user.setUserType(UserType.ADMIN);
            return jwtUser.generateToken(user);
        }
        UserCredentials userCredentials = userCredentialsRepository.findByPasswordAndUserName(password, userName).orElseThrow(() -> new AuthException("login failed - user credentials illegal"));
        user.setUserName(userName);
        user.setUserType(userCredentials.getUserType());
        return jwtUser.generateToken(user);
    }


    public String register(Company company) throws JsonProcessingException, AuthException {
        if (!userCredentialsRepository.existsByUserName(company.getUserCredentials().getUserName())) {
            company.getUserCredentials().setUserType(UserType.COMPANY);
            userCredentialsRepository.save(company.getUserCredentials());
            addressRepository.save(company.getAddress());
            companyRepository.save(company);
            user.setUserName(company.getUserCredentials().getUserName());
            user.setUserType(UserType.COMPANY);
            return jwtUser.generateToken(user);
        } else
            throw new AuthException("register failed - this user name already exists");
    }

}
