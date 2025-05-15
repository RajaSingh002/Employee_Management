package controller;

import model.EmployeeModel;
import repository.EmployeeRepo;
import service.LoginService;
import utils.DatabaseUtil;

import view.LoginView;

public class LoginController {

    private final LoginView view;
    private final LoginService service;
    private final String companyName;

    public LoginController(String companyName) {
        this.companyName = companyName;
        this.view = new LoginView(companyName);
        this.service = new LoginService(new EmployeeRepo(DatabaseUtil.getConnection()));
    }

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
