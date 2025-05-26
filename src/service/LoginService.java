
package service;

import model.EmployeeModel;
import repository.EmployeeRepo;

/*
*********************************************************
 *  @Class Name     : LoginService
 *  @author         : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company        : Antrazal
 *  @description    : Handles user login logic using EmployeeRepo
*********************************************************
 */
public class LoginService {

    private final EmployeeRepo userRepository;

    /*
     *********************************************************
     * @Method Name : LoginService (Constructor)
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Initializes the LoginService with EmployeeRepo
     * 
     * @param : EmployeeRepo userRepository
     * 
     * @return : N/A
     *********************************************************
     */
    public LoginService(EmployeeRepo userRepository) {
        this.userRepository = userRepository;
    }

    /*
     *********************************************************
     * @Method Name : login
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Authenticates a user by username, password, and company name
     * 
     * @param : String username, String password, String CompanyName
     * 
     * @return : EmployeeModel if authentication is successful; otherwise null
     *********************************************************
     */
    public EmployeeModel login(String username, String password, String CompanyName) {
        EmployeeModel user = userRepository.findByUsername(username, CompanyName);

        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}
