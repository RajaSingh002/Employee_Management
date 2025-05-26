package controller;

import java.sql.Connection;

import model.EmployeeModel;
import view.EmployeeView;

/*
 *******************************************************************************************************
 *   @Parent Class Name  :   EmployeeController
 *   @Class Name         :   TechLeadController
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   Controller class for Tech Lead role. Handles attendance, timesheet,
 *                           leave application, skill updates, and provides dashboard interaction
 *                           specific to Tech Leads.
 *******************************************************************************************************
 */
public class TechLeadController extends EmployeeController {

    protected AttendanceController aController;
    protected TimeSheetController tController;

    public TechLeadController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.aController = new AttendanceController(user, conn);
        this.tController = new TimeSheetController(user, conn);
    }

    /*
     ***********************************************************************************************
     * @Method Name : showTechLeadDashboard
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Displays the Tech Lead dashboard menu and handles
     * user-selected options.
     * 
     * @Return : boolean - false if logout, true if back to main menu
     ***********************************************************************************************
     */
    public boolean showTechLeadDashboard() {
        while (true) {
            EmployeeView.showTechLeadMenu();
            int choice = EmployeeView.getChoice();

            switch (choice) {
                case 1:
                    leaveController.applyLeave();
                    break;
                case 2:
                    tController.addTimesheet();
                    break;
                case 3:
                    aService.clockIn(user.getId(), user.getCompanyId());
                    break;
                case 4:
                    aService.clockOut(user.getId(), user.getCompanyId());
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
