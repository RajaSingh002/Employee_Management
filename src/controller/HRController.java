package controller;

import java.sql.Connection;

import model.EmployeeModel;
import view.EmployeeView;

public class HRController extends EmployeeController {
    protected  AttendanceController aController;
    public HRController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.aController=new AttendanceController(user, conn);
    }

    public boolean showHRDashboard() {
        while (true) {
            EmployeeView.showHrMenu();
            int choice = EmployeeView.getChoice();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    UpdateEmployee();
                    break;
                case 3:
                    DeleteEmployee();
                    break;
                case 4:
                    aService.clockIn(user.getId(), user.getCompanyId());
                    break;
                case 5:
                    aService.clockOut(user.getId(), user.getCompanyId());
                    break;
                case 6:
                    aController.showTodayAttendance();
                    break;
                case 7:
                   aController.showAttendanceHistory();
                    break;
                case 8:
                    generateSalarySlipForEmployee();
                    break;
                case 9:
                    handleAddSkills();
                    break;
                case 10:
                    ViewAll();
                    break;
                case 11:
                  EmployeeView.showLogoutSuccess();
                    return false;
                case 12:
                  return true;
                default:
                  EmployeeView.showInvalidChoice();
            }
        }
    }
}
