
package service;

import model.EmployeeModel;
import repository.EmployeeRepo;

public class LoginService {

    private final EmployeeRepo userRepository;

    public LoginService(EmployeeRepo userRepository) {
        this.userRepository = userRepository;
    }

    public EmployeeModel login(String username, String password, String CompanyName) {
        EmployeeModel user = userRepository.findByUsername(username, CompanyName);

        if (user != null &&user.getUsername().equals(username) &&user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
