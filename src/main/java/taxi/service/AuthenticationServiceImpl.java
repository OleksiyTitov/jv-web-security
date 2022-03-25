package taxi.service;

import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isEmpty()) {
            throw new AuthenticationException("Login or password are incorrect");
        }
        String driverPassword = driver.get().getPassword();
        if(password.equals(driverPassword)) {
            return driver.get();
        }
        throw new AuthenticationException("Login or password are incorrect");
    }
}