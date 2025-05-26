package controller;

import model.EmployeeModel;
import repository.EmployeeRepo;
import service.LoginService;
import utils.DatabaseUtil;
import view.LoginView;

/*
*******************************************************************************************************
*   @Class Name         : LoginController
*   @Author             : <Raja Kumar>(raja.kumar@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 26-05-2025
*   @Description        : Controller handling login workflow for users across companies
*******************************************************************************************************
*/
public class LoginController {

    private final LoginView view;
    private final LoginService service;
    private final String companyName;

    /*
     *********************************************************
     * @Method Name : LoginController (Constructor)
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Initializes LoginController with company context and services
     * 
     * @Param : String companyName
     * 
     * @Return : void
     ********************************************************
     */
    public LoginController(String companyName) {
        this.companyName = companyName;
        this.view = new LoginView(companyName);
        this.service = new LoginService(new EmployeeRepo(DatabaseUtil.getConnection()));
    }

    /*
     *********************************************************
     * @Method Name : handleLogin
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Handles login interaction loop with user input and
     * authentication
     * 
     * @Param : none
     * 
     * @Return : EmployeeModel logged-in user or null on exit/failure
     ********************************************************
     */
    public EmployeeModel handleLogin() {

        while (true) {
            view.showLoginScreen();

            String username = view.getUsername();
            String password = view.getPassword();

            EmployeeModel user = service.login(username, password, companyName);

            if (user != null) {
                view.showLoginSuccess(user.getFirstName(), user.getPosition());
                return user;
            } else {
                view.showLoginFailure();
                String choice = view.askRetry();
                if (!choice.equalsIgnoreCase("yes")) {
                    view.showExitMessage();
                    return null;
                }
            }
        }
    }
}
