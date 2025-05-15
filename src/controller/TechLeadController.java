package controller;

import java.sql.Connection;

import model.EmployeeModel;
import utils.Constant;
import view.EmployeeView;

public class TechLeadController extends EmployeeController {
    protected AttendanceController aController;
    protected TimeSheetController tController;

    public TechLeadController(EmployeeModel user, Connection conn) {
        super(user, conn);
        this.aController=new AttendanceController(user, conn);
        this.tController=new TimeSheetController(user, conn);
    }

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
                    System.out.println(Constant.LOGOUT_SUCCESS); 
                    return false;
                case 8:
                    return true;
                default:
                     System.out.println(Constant.INVALID_CHOICE);
            }
        }
    }

}
