
package controller;

import java.sql.Connection;
import model.EmployeeModel;
import service.AttendanceService;
import service.CompanySignUp;
import service.LeaveService;
import utils.ClearScreen;
import utils.Constant;
import utils.DatabaseInitializer;
import utils.DatabaseUtil;
import view.MainView;

public class MainController {
    protected EmployeeModel user;
    protected AttendanceService aService;
    protected LeaveController leaveController;
    protected CEOController ceoController;
    protected ManagerController mController;
    protected TechLeadController tControlller;
    protected HRController hController;
    protected DeveloperController dController;
    private Connection conn;

    public MainController(EmployeeModel user) {
        this.user = user;
    }

    public boolean showDashboard() {
        switch (user.getPosition().toUpperCase()) {
            case "CEO":
                return ceoController.showCEODashboard();
            case "MANAGER":
                mController.showManagerDashboard();
                break;
            case "TECHLEAD":
                tControlller.showTechLeadDashboard();
                break;
            case "HR":
                hController.showHRDashboard();
                break;
            default:
                return dController.showDeveloperDashboard();
        }
        return false;
    }

    public void run() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            this.conn = conn;
            DatabaseInitializer.initializeDatabase();
            CompanySignUp companyService = new CompanySignUp();

            while (true) {
                MainView.showMainMenu();
                int choice = MainView.getChoice();

                switch (choice) {
                    case 1 -> new RegistrationController().startRegistration();
                    case 2 -> handleLoginFlow(companyService);
                    case 3 -> {
                        MainView.showExitMessage();
                        return;
                    }
                    default -> MainView.showInvalidMainMenuChoice();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLoginFlow(CompanySignUp companyService) {
        while (true) {
            ClearScreen.getInstance().clearScreen();

            String companyName = MainView.getCompanyNameOrBack();
            if (companyName.equalsIgnoreCase(Constant.BACK))
                return;

            if (!companyService.isCompanyRegistered(companyName)) {
                MainView.showCompanyNotFound();
                String retry = MainView.getRetryChoice();
                if (!retry.equals("y"))
                    return;
                continue;
            }

            while (true) {
                ClearScreen.getInstance().clearScreen();
                MainView.showLoginHeader(companyName);

                LoginController loginController = new LoginController(companyName);
                this.user = loginController.handleLogin();

                if (user != null) {
                    MainController controller = new MainController(user);
                    controller.aService = new AttendanceService(conn);
                    controller.leaveController = new LeaveController(user, new LeaveService(conn));
                    controller.ceoController = new CEOController(user, conn);
                    controller.mController = new ManagerController(user, conn);
                    controller.tControlller = new TechLeadController(user, conn);
                    controller.hController = new HRController(user, conn);
                    controller.dController = new DeveloperController(user, conn);

                    boolean exitToMain = controller.showDashboard();

                    ClearScreen.getInstance().clearScreen();
                    MainView.showLogoutMessage();
                    if (exitToMain)
                        return;
                } else {
                    MainView.showLoginFailed();
                }
            }
        }
    }
}
