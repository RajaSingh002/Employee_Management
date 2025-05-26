
package view;

import java.util.List;
import model.TimeSheetModel;
import utils.Constant;

/*
*********************************************************************************************************
 *  @Class Name      : TimeSheetView   
 *  @Author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 12-05-2025
 *  @Description     : View class for displaying timesheet related messages and prompts
 * 
 *******************************************************************************************************
*/

public class TimeSheetView {

    /*
     *********************************************************
     * @Method Name : showTimesheetAlreadyFilled
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when timesheet for the day is already filled
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showTimesheetAlreadyFilled() {
        System.out.println(Constant.TIMESHEET_ALREADY_FILLED);
    }

    /*
     *********************************************************
     * @Method Name : TaskDescription
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter task description for timesheet
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void TaskDescription() {
        System.out.print(Constant.TASK_DESCRIPTION_INPUT);
    }

    /*
     *********************************************************
     * @Method Name : showTaskEmptyError
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error when task description is empty
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showTaskEmptyError() {
        System.out.println(Constant.TASK_EMPTY_ERROR);
    }

    /*
     *********************************************************
     * @Method Name : HoursWorked
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter hours worked
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void HoursWorked() {
        System.out.print(Constant.HOURS_WORKED_INPUT);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidHoursRange
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error when entered hours are out of valid range
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidHoursRange() {
        System.out.println(Constant.INVALID_HOURS_RANGE);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidNumericInput
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error when input is not a valid number
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidNumericInput() {
        System.out.println(Constant.INVALID_NUMBER_INPUT);
    }

    /*
     *********************************************************
     * @Method Name : showTimesheetAddedSuccess
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays success message when timesheet is added
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showTimesheetAddedSuccess() {
        System.out.println(Constant.TIMESHEET_ADDED_SUCCESS);
    }

    /*
     *********************************************************
     * @Method Name : showTimesheetAddFailure
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays failure message when timesheet addition fails
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showTimesheetAddFailure() {
        System.out.println(Constant.TIMESHEET_ADD_FAILURE);
    }

    /*
     *********************************************************
     * @Method Name : showAccessDeniedForTimesheets
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays access denied message for unauthorized timesheet
     * access
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showAccessDeniedForTimesheets() {
        System.out.println(Constant.ACCESS_DENIED_FOR_TIMESHEETS);
    }

    /*
     *********************************************************
     * @Method Name : showNoTimesheetDataFound
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when no timesheet data is found
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoTimesheetDataFound() {
        System.out.println(Constant.NO_TIMESHEET_DATA_FOUND);
    }

    /*
     *********************************************************
     * @Method Name : HoursWorkedWithRemaining
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter hours worked with remaining hours info
     * 
     * @param : double remaining - remaining hours allowed to log
     * 
     * @return : void
     ********************************************************
     */
    public static void HoursWorkedWithRemaining(double remaining) {
        System.out.print(String.format(Constant.HOURS_WORKED_WITH_REMAINING, remaining));
    }

    /*
     *********************************************************
     * @Method Name : showInvalidHoursForRemaining
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error when entered hours exceed remaining allowed
     * hours
     * 
     * @param : double remaining - remaining hours allowed to log
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidHoursForRemaining(double remaining) {
        System.out.println(String.format(Constant.INVALID_HOURS_FOR_REMAINING, remaining));
    }

    /*
     *********************************************************
     * @Method Name : showTimesheetFullyLogged
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when timesheet is fully logged for the day
     * 
     * @param : None
     * 
     * @return : void
     ********************************************************
     */
    public static void showTimesheetFullyLogged() {
        System.out.println(Constant.TIMESHEET_FULLY_LOGGED);
    }

    /*
     *********************************************************
     * @Method Name : displayAllTimesheets
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays list of all timesheets in tabular form
     * 
     * @param : List<TimeSheetModel> list - list of timesheets
     * 
     * @return : void
     ********************************************************
     */
    public static void displayAllTimesheets(List<TimeSheetModel> list) {
        System.out.println(Constant.TIMESHEET_HEADER);
        System.out.printf(Constant.TIMESHEET_HEADERs, "Emp ID", "Date", "Task Description", "Hours");
        for (TimeSheetModel ts : list) {
            Constant.showTimesheet(ts);
        }
    }

}
