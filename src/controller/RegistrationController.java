package controller;

import java.sql.Connection;
import java.time.LocalDate;

import model.CompanyModel;
import model.EmployeeModel;
import service.AttendanceService;
import service.CompanySignUp;
import service.LeaveService;
import utils.Constant;
import utils.DatabaseUtil;
import view.*;

/*
 *******************************************************************************************************
 *   @Class Name         :   RegistrationController
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   Handles company registration and CEO onboarding. If company is already 
 *                           registered, prompts for login. Initializes controller instances after login.
 *******************************************************************************************************
 */
public class RegistrationController {

    protected EmployeeModel user;
    protected AttendanceService aService;
    protected LeaveController leaveController;
    public CEOController ceoController;
    public ManagerController mController;
    public TechLeadController tControlller;
    public HRController hController;
    public DeveloperController dController;

    private CompanySignUp companyService = new CompanySignUp();
    private RegistrationView reg = new RegistrationView();
    Connection conn = DatabaseUtil.getConnection();

    /*
     ***********************************************************************************************
     * @Method Name : startRegistration
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Registers a new company and CEO or redirects to login if
     * already registered.
     ***********************************************************************************************
     */
    public void startRegistration() {
        String Cname = reg.getCompanyName();
        if (Cname.equalsIgnoreCase(Constant.BACK))
            return;

        String username = reg.getUsername();
        if (username.equalsIgnoreCase(Constant.BACK))
            return;

        while (companyService.isUserAlreadyExits(username)) {
            reg.showUserNameExistsMessage();
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
            reg.showEmailExistsMessage();
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

    /*
     ***********************************************************************************************
     * @Method Name : promptLoginAndShowMenu
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Prompts for user login and initializes all controllers based
     * on role.
     * 
     * @Param : companyName - The name of the company for login
     ***********************************************************************************************
     */
    private void promptLoginAndShowMenu(String companyName) {
        LoginView loginView = new LoginView(companyName);
        loginView.showLoginScreen();

        while (true) {
            LoginController loginController = new LoginController(companyName);
            EmployeeModel user = loginController.handleLogin();

            if (user != null) {
                try (Connection conn = DatabaseUtil.getConnection()) {
                    MainController controller = new MainController(user);
                    controller.aService = new AttendanceService(conn);
                    controller.leaveController = new LeaveController(user, new LeaveService(conn));
                    controller.ceoController = new CEOController(user, conn);
                    controller.mController = new ManagerController(user, conn);
                    controller.tControlller = new TechLeadController(user, conn);
                    controller.hController = new HRController(user, conn);
                    controller.dController = new DeveloperController(user, conn);

                    controller.showDashboard();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                reg.showLoginFailedMessage();
            }
        }
    }
}
