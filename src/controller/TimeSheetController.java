package controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.*;
import service.TimeSheetService;
import utils.Constant;
import utils.ScannerSingleton;
import view.TimeSheetView;

/*
 *******************************************************************************************************
 *   @Parent Class Name  :   None
 *   @Class Name         :   TimeSheetController
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   Controller class responsible for handling timesheet functionality
 *                           such as adding timesheet entries and showing all timesheets
 *                           (restricted to CEO view).
 *******************************************************************************************************
 */
public class TimeSheetController {
    protected final EmployeeModel user;
    private final TimeSheetService tService;

    public TimeSheetController(EmployeeModel user, Connection conn) {
        this.user = user;
        this.tService = new TimeSheetService(conn);
    }

    /*
     ***********************************************************************************************
     * @Method Name : addTimesheet
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Allows an employee to log up to 8 hours of work in their
     * timesheet
     ***********************************************************************************************
     */
    public void addTimesheet() {
        final int empId = user.getId();
        final int companyId = user.getCompanyId();
        final LocalDate today = LocalDate.now();

        double totalHoursLogged = tService.getTotalHoursForDate(empId, companyId, today);

        if (totalHoursLogged >= 8) {
            TimeSheetView.showTimesheetAlreadyFilled();
            return;
        }

        Scanner sc = ScannerSingleton.getInstance();

        while (totalHoursLogged < 8) {
            double remainingHours = 8 - totalHoursLogged;

            String task;
            while (true) {
                TimeSheetView.TaskDescription();
                task = sc.nextLine().trim();
                if (task.equalsIgnoreCase(Constant.BACK))
                    return;
                if (!task.isEmpty())
                    break;
                TimeSheetView.showTaskEmptyError();
            }

            double hours = -1;
            while (true) {
                TimeSheetView.HoursWorkedWithRemaining(remainingHours);
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase(Constant.BACK))
                    return;

                try {
                    hours = Double.parseDouble(input);
                    if (hours > 0 && hours <= remainingHours)
                        break;
                    else
                        TimeSheetView.showInvalidHoursForRemaining(remainingHours);
                } catch (NumberFormatException e) {
                    TimeSheetView.showInvalidNumericInput();
                }
            }

            TimeSheetModel ts = new TimeSheetModel();
            ts.setEmpId(empId);
            ts.setCompanyId(companyId);
            ts.setDate(today);
            ts.setDescription(task);
            ts.setHoursWorked(hours);

            if (tService.addTimesheet(ts)) {
                TimeSheetView.showTimesheetAddedSuccess();
                totalHoursLogged += hours;

                if (totalHoursLogged >= 8) {
                    TimeSheetView.showTimesheetFullyLogged();
                    return;
                }
            } else {
                TimeSheetView.showTimesheetAddFailure();
                return;
            }
        }
    }

    /*
     ***********************************************************************************************
     * @Method Name : showAllTimesheets
     * 
     * @Author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @Description : Displays the complete timesheet history for all employees.
     * Access restricted to the CEO role.
     ***********************************************************************************************
     */
    public void showAllTimesheets() {
        if (!user.getPosition().equals("CEO")) {
            TimeSheetView.showAccessDeniedForTimesheets();
            return;
        }

        List<TimeSheetModel> list = tService.getTimesheetHistory(user.getCompanyId());
        if (list.isEmpty()) {
            TimeSheetView.showNoTimesheetDataFound();
            return;
        }

        TimeSheetView.displayAllTimesheets(list);
    }
}
