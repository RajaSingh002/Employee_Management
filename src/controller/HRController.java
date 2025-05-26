package controller;

import java.sql.Connection;

import model.EmployeeModel;
import view.EmployeeView;

/*
*******************************************************************************************************
*   @Parent Class Name  : EmployeeController
*   @Class Name         : HRController
*   @Author             : <Raja Kumar>(raja.kumar@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 26-05-2025
*   @Description        : Controller for HR role, extends EmployeeController
*                         Handles HR-specific dashboard and functionalities
*******************************************************************************************************
*/

public class HRController extends EmployeeController {
    protected AttendanceController aController;

    /*
     *********************************************************
     * @Method Name : HRController (Constructor)
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Initializes HRController and AttendanceController
     * 
     * @param : EmployeeModel user, Connection conn
     * 
     * @return : void
     ********************************************************
     */
    public HRController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.aController = new AttendanceController(user, conn);
    }

    /*
     *********************************************************
     * @Method Name : showHRDashboard
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays HR dashboard menu and processes user input choices
     * 
     * @param : none
     * 
     * @return : boolean (true to continue, false to logout)
     ********************************************************
     */
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
