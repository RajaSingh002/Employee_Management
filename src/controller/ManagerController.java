package controller;

import java.sql.Connection;
import model.EmployeeModel;
import view.EmployeeView;

/*
 *******************************************************************************************************
 *   @Parent Class Name  :   EmployeeController
 *   @Class Name         :   ManagerController
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   Handles manager-specific operations including leave management, attendance,
 *                           timesheet entry, skill updates, and team attendance viewing.
 *******************************************************************************************************
 */
public class ManagerController extends EmployeeController {

    protected AttendanceController aController;
    protected TimeSheetController tController;

    /*
     ***********************************************************************************************
     * @Method Name : ManagerController (Constructor)
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Initializes ManagerController with required services for a
     * logged-in manager.
     * 
     * @Param : user - Logged-in manager
     * : conn - Active database connection
     ***********************************************************************************************
     */
    public ManagerController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.aController = new AttendanceController(user, conn);
        this.tController = new TimeSheetController(user, conn);
    }

    /*
     ***********************************************************************************************
     * @Method Name : showManagerDashboard
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Displays and handles the manager dashboard options and
     * actions.
     * 
     * @Return : boolean - true to return to main menu, false to log out
     ***********************************************************************************************
     */
    public boolean showManagerDashboard() {
        while (true) {
            EmployeeView.showManagerMenu();
            int choice = EmployeeView.getChoice();

            switch (choice) {
                case 1 -> leaveController.approveLeave();
                case 2 -> leaveController.applyLeave();
                case 3 -> tController.addTimesheet();
                case 4 -> aService.clockIn(user.getId(), user.getCompanyId());
                case 5 -> aService.clockOut(user.getId(), user.getCompanyId());
                case 6 -> handleAddSkills();
                case 7 -> aController.showTodayAttendance();
                case 8 -> {
                    EmployeeView.showLogoutSuccess();
                    return false;
                }
                case 9 -> {
                    return true;
                }
                default -> EmployeeView.showInvalidChoice();
            }
        }
    }
}
