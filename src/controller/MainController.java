package controller;

import java.sql.Connection;
import java.util.Scanner;
import model.*;
import service.AttendanceService;
import service.CompanySignUp;
import service.LeaveService;
import utils.ClearScreen;
import utils.Constant;
import utils.DatabaseInitializer;
import utils.DatabaseUtil;
import utils.ScannerSingleton;

public class MainController {
    protected EmployeeModel user;
    protected AttendanceService aService;
    protected LeaveController leaveController;
    private CEOController ceoController;
    private ManagerController mController;
    private TechLeadController tControlller;
    private HRController hController;
    private DeveloperController dController;
    private Connection conn;

    public MainController() {
       
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
            Scanner scanner = ScannerSingleton.getInstance();

            while (true) {
                Constant.showMainMenu();

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(Constant.INVALID_NUMBER_INPUT);
                    continue;
                }

                switch (choice) {
                    case 1 -> new RegistrationController().startRegistration();
                    case 2 -> handleLoginFlow(companyService, scanner);
                    case 3 -> {
                        System.out.println(Constant.EXIT_MESSAGE);
                        return;
                    }
                    default -> System.out.println(Constant.INVALID_MAIN_MENU_CHOICE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLoginFlow(CompanySignUp companyService, Scanner scanner) {
        while (true) {
            ClearScreen.getInstance().clearScreen();
            System.out.println(Constant.ENTER_COMPANY_NAME_OR_BACK);
            String companyName = scanner.nextLine();
            if (companyName.equalsIgnoreCase(Constant.BACK))
                return;

            if (!companyService.isCompanyRegistered(companyName)) {
                System.out.println(Constant.COMPANY_NOT_FOUND);
                System.out.println(Constant.TRY_ANOTHER_COMPANY);
                String retry = scanner.nextLine().trim().toLowerCase();
                if (!retry.equals("y"))
                    return;
                continue;
            }

            while (true) {
                ClearScreen.getInstance().clearScreen();
                System.out.println(Constant.LOGIN_HEADER_PREFIX+companyName);
                LoginController loginController = new LoginController(companyName);
                this.user = loginController.handleLogin(); 

                if (user != null) {
                    this.aService = new AttendanceService(conn);
                    this.leaveController = new LeaveController(user, new LeaveService(conn));
                    this.ceoController = new CEOController(user, conn);
                    this.mController = new ManagerController(user, conn);
                    this.tControlller = new TechLeadController(user, conn);
                    this.hController = new HRController(user, conn);
                    this.dController = new DeveloperController(user, conn);

                     boolean exitToMain = showDashboard();

                    ClearScreen.getInstance().clearScreen();
                   System.out.println(Constant.LOGOUT_MESSAGE);
                     if (exitToMain) return;
                } else {
                    System.out.println(Constant.LOGIN_FAILED);
                }
            }
        }
    }
}
