package view;

import java.util.List;

import model.TimeSheetModel;
import utils.Constant;

public class TimeSheetView {
    public static void showTimesheetAlreadyFilled() {
        System.out.println(Constant.TIMESHEET_ALREADY_FILLED);
    }

    public static void TaskDescription() {
        System.out.print(Constant.TASK_DESCRIPTION_INPUT);
    }

    public static void showTaskEmptyError() {
        System.out.println(Constant.TASK_EMPTY_ERROR);
    }

    public static void HoursWorked() {
        System.out.print(Constant.HOURS_WORKED_INPUT);
    }

    public static void showInvalidHoursRange() {
        System.out.println(Constant.INVALID_HOURS_RANGE);
    }

    public static void showInvalidNumericInput() {
        System.out.println(Constant.INVALID_NUMBER_INPUT);
    }

    public static void showTimesheetAddedSuccess() {
        System.out.println(Constant.TIMESHEET_ADDED_SUCCESS);
    }

    public static void showTimesheetAddFailure() {
        System.out.println(Constant.TIMESHEET_ADD_FAILURE);
    }

    public static void showAccessDeniedForTimesheets() {
        System.out.println(Constant.ACCESS_DENIED_FOR_TIMESHEETS);
    }

    public static void showNoTimesheetDataFound() {
        System.out.println(Constant.NO_TIMESHEET_DATA_FOUND);
    }

    public static void HoursWorkedWithRemaining(double remaining) {
        System.out.print(String.format(Constant.HOURS_WORKED_WITH_REMAINING, remaining));
    }

    public static void showInvalidHoursForRemaining(double remaining) {
        System.out.println(String.format(Constant.INVALID_HOURS_FOR_REMAINING, remaining));
    }

    public static void showTimesheetFullyLogged() {
        System.out.println(Constant.TIMESHEET_FULLY_LOGGED);
    }

    public static void displayAllTimesheets(List<TimeSheetModel> list) {
        System.out.println(Constant.TIMESHEET_HEADER);
        System.out.printf(Constant.TIMESHEET_HEADERs, "Emp ID", "Date", "Task Description", "Hours");
        for (TimeSheetModel ts : list) {
            Constant.showTimesheet(ts);
        }
    }

}
