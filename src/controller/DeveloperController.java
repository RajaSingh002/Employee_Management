package controller;

import java.sql.Connection;
import model.EmployeeModel;
import utils.Constant;
import view.EmployeeView;

public class DeveloperController extends EmployeeController {
     protected  AttendanceController aController;
     protected TimeSheetController tController;
    public DeveloperController(EmployeeModel User, Connection conn) {
        super(User, conn);
        this.tController = new TimeSheetController(user, conn);
        this.aController=new AttendanceController(User, conn);
    }

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
