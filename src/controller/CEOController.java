package controller;

import java.sql.Connection;
import model.EmployeeModel;
import utils.Constant;
import view.EmployeeView;

public class CEOController extends EmployeeController {
    protected AttendanceController aController;
    protected TimeSheetController tController;

    public CEOController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.tController = new TimeSheetController(user, conn);
        this.aController = new AttendanceController(user, conn);
    }

    public boolean showCEODashboard() {
        while (true) {
            EmployeeView.showCEOMenu();
            int choice = EmployeeView.getChoice();
            switch (choice) {
                case 1:
                    showHierarchy();
                    break;
                case 2:
                    ViewAll();
                    break;
                case 3:
                    aService.clockIn(user.getId(), user.getCompanyId());
                    break;
                case 4:
                    aService.clockOut(user.getId(), user.getCompanyId());
                    break;
                case 5:
                    tController.showAllTimesheets();
                    break;
                case 6:
                    leaveController.approveLeave();
                    break;
                case 7:
                  System.out.println(Constant.LOGOUT_SUCCESS); 
                    return false;
                case 8:
                    aController.showWorkingLogReport();
                    break;
                case 9:
                    aController.showTodayAttendance();
                    break;
                case 10:
                    addEmployee();
                    break;
                case 11:
                    return true;
                default:
                    System.out.println(Constant.INVALID_CHOICE);
            }
        }
    }
}
