package controller;

import java.sql.Connection;
import model.EmployeeModel;
import view.EmployeeView;

/*
 *******************************************************************************************************
 *   @Parent Class Name  :   EmployeeController
 *   @Class Name         :   DeveloperController
 *   @Author             :   raja.kumar@antrazal.com
 *   @Company            :   Antrazal
 *   @Date               :   12-05-2025
 *   @Description        :   Handles Developer role actions such as clock-in/out, timesheet logging,
 *                           leave applications, skill updates, and viewing today's attendance.
 *******************************************************************************************************
 */
public class DeveloperController extends EmployeeController {
    protected AttendanceController aController;
    protected TimeSheetController tController;

    public DeveloperController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.tController = new TimeSheetController(user, conn);
        this.aController = new AttendanceController(user, conn);
    }

    /*
     ***************************************************************************************************
     *  @Method Name       :   showDeveloperDashboard
     *  @Author            :   raja.kumar@antrazal.com
     *  @Company           :   Antrazal
     *  @Description       :   Displays Developer dashboard menu and handles action routing.
     *  @Return            :   boolean (false on logout, true to go back)
     ***************************************************************************************************
     */
    public boolean showDeveloperDashboard() {
        while (true) {
            EmployeeView.showDeveloperMenu();
            int choice = EmployeeView.getChoice();

            switch (choice) {
                case 1:
                    aService.clockIn(user.getId(), user.getCompanyId());
                    break;
                case 2:
                    aService.clockOut(user.getId(), user.getCompanyId());
                    break;
                case 3:
                    tController.addTimesheet();
                    break;
                case 4:
                    leaveController.applyLeave();
                    break;
                case 5:
                    handleAddSkills();
                    break;
                case 6:
                    aController.showTodayAttendance();
                    break;
                case 7:
                    EmployeeView.showLogoutSuccess();
                    return false;
                case 8:
                    return true;
                default:
                    EmployeeView.showInvalidChoice();
            }
        }
    }
}
