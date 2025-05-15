package controller;

import java.sql.Connection;

import model.EmployeeModel;
import utils.Constant;
import view.EmployeeView;

public class ManagerController extends EmployeeController {
     protected  AttendanceController aController;
     protected TimeSheetController tController;
    public ManagerController(EmployeeModel user,Connection conn){
     super(user, conn);
     this.aController=new AttendanceController(user, conn);
     this.tController=new TimeSheetController(user, conn);
    }
     public boolean showManagerDashboard() {
        while (true) {

            EmployeeView.showManagerMenu();

            int choice = EmployeeView.getChoice();

            switch (choice) {
                case 1:
                    leaveController.approveLeave();
                    break;
                case 2:
                    leaveController.applyLeave();
                    break;
                case 3:
                   tController.addTimesheet();
                    break;
                case 4:
                    aService.clockIn(user.getId(), user.getCompanyId());
                    break;
                case 5:
                    aService.clockOut(user.getId(), user.getCompanyId());
                    break;
                case 6:
                   handleAddSkills();
                    break;
                case 7:
                   aController.showTodayAttendance();
                    break;
                case 8:
                   System.out.println(Constant.LOGOUT_SUCCESS); 
                    return false;
                case 9:
                 return true;
                default:
                    System.out.println(Constant.INVALID_CHOICE);
            }
        }
    }

}
