package controller;

import java.sql.Connection;
import java.time.LocalDate;

import model.CompanyModel;
import model.EmployeeModel;
import service.CompanySignUp;
import utils.Constant;
import utils.DatabaseUtil;
import view.*;

public class RegistrationController {
    private CompanySignUp companyService = new CompanySignUp();
    private RegistrationView reg = new RegistrationView();
    Connection conn = DatabaseUtil.getConnection();

    public void startRegistration() {
        String Cname = reg.getCompanyName();
        if (Cname.equalsIgnoreCase(Constant.BACK))
            return;

        String username = reg.getUsername();
        if (username.equalsIgnoreCase(Constant.BACK))
            return;

        while (companyService.isUserAlreadyExits(username)) {
           System.out.println(Constant.USER_NAME_ALREADY_EXISTS);
            username = reg.getUsername();
            if (username.equalsIgnoreCase(Constant.BACK))
                return;
        }

        String FirstName = reg.getFirstName();
        if (FirstName.equalsIgnoreCase(Constant.BACK))
            return;

        String LastName = reg.getLastName();
        if (LastName.equalsIgnoreCase(Constant.BACK))
            return;

        String Email = reg.getPersonalEmail();
        if (Email.equalsIgnoreCase(Constant.BACK))
            return;

        String password = reg.getPassword();
        if (password.equalsIgnoreCase(Constant.BACK))
            return;

        String email = reg.getEmail();
        if (email.equalsIgnoreCase(Constant.BACK))
            return;

        while (companyService.isEmailAlreadyExits(email)) {
            System.out.println(Constant.EMAIL_ALREADY_EXISTS);
            email = reg.getEmail();
            if (email.equalsIgnoreCase(Constant.BACK))
                return;
        }

        if (companyService.isCompanyRegistered(Cname)) {
            reg.showAlreadyRegisteredMessage();
            promptLoginAndShowMenu(Cname);
        } else {
            CompanyModel company = new CompanyModel(Cname, username, password, email);
            EmployeeModel employee = new EmployeeModel();
            employee.setFirstName(FirstName);
            employee.setLastName(LastName);
            employee.setEmail(Email);
            employee.setPosition("CEO");
            employee.setActive(true);
            employee.addSkill(employee.CeoSkills());
            employee.setDepartmentName(null);
            employee.setJoiningDate(LocalDate.now());
            employee.setManagerId(null);
            companyService.registerCompany(company, employee);
            reg.showRegistrationSuccess();
            promptLoginAndShowMenu(Cname);
        }
    }

    private void promptLoginAndShowMenu(String companyName) {
        LoginView loginView = new LoginView(companyName);
        loginView.showLoginScreen();
        while (true) {
            LoginController loginController = new LoginController(companyName);
            EmployeeModel user = loginController.handleLogin();

            if (user != null) {
                MainController controller = new MainController();
                controller.showDashboard();
            } else {
                System.out.println(Constant.LOGIN_FAILED);
            }
        }

    }
}
