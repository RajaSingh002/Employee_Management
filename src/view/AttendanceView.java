/*
*********************************************************************************************************
 *  @Apex Class Name :   AttendanceView
 *  @Author          :   Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         :   Antrazal
 *  @Date            :   26-05-2025
 *  @Description     :   View class to display attendance related information and messages
 * 
 *******************************************************************************************************
*/

package view;

import java.util.List;

import model.AttendanceModel;
import utils.Constant;

public class AttendanceView {

    /*
     *********************************************************
     * @Method Name : showNoAttendanceForToday
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message indicating no attendance recorded for today
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoAttendanceForToday() {
        System.out.println(Constant.TODAY_ATTENDANCE);
    }

    /*
     *********************************************************
     * @Method Name : displayTodayAttendance
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays today's attendance details including in-time,
     * out-time and status
     * 
     * @param : AttendanceModel today - attendance record for the current day
     * 
     * @return : void
     ********************************************************
     */
    public static void displayTodayAttendance(AttendanceModel today) {
        Constant.View1();
        String inTime = today.getInTime() != null ? today.getInTime().toString() : "N/A";
        String outTime = today.getOutTime() != null ? today.getOutTime().toString() : "N/A";
        String status = today.getStatus();

        System.out.printf("| %-19s | %-19s | %-9s |\n", inTime, outTime, status);
        System.out.println(Constant.LINE);
    }

    /*
     *********************************************************
     * @Method Name : showNoAttendanceHistory
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message indicating no attendance history found
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoAttendanceHistory() {
        System.out.println(Constant.NO_ATTENDANCE);
    }

    /*
     *********************************************************
     * @Method Name : displayAttendanceHistory
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays attendance records list with employee ID, date,
     * in-time, out-time and status
     * 
     * @param : List<AttendanceModel> history - list of attendance records
     * 
     * @return : void
     ********************************************************
     */
    public static void displayAttendanceHistory(List<AttendanceModel> history) {
        Constant.View();
        for (AttendanceModel att : history) {
            String empId = "EID " + att.getEmpId();
            String date = att.getDate().toString();
            String inTime = att.getInTime() != null ? att.getInTime().toString() : "N/A";
            String outTime = att.getOutTime() != null ? att.getOutTime().toString() : "N/A";
            String status = att.getStatus();

            System.out.printf("| %-8s | %-10s | %-19s | %-19s | %-9s |\n", empId, date, inTime, outTime, status);
        }
        System.out.println(Constant.LINE);
    }

    /*
     *********************************************************
     * @Method Name : EmployeeId
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter Employee ID
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void EmployeeId() {
        System.out.print(Constant.EMPLOYEE_ID);
    }

    /*
     *********************************************************
     * @Method Name : StartDate
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter start date for attendance queries
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void StartDate() {
        System.out.print(Constant.START_DATE);
    }

    /*
     *********************************************************
     * @Method Name : EndDate
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter end date for attendance queries
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void EndDate() {
        System.out.print(Constant.END_DATE);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidEmployeeId
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error message for invalid employee ID input
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidEmployeeId() {
        System.out.println(Constant.INVALID_EMPLOYEE_ID);
    }

    /*
     *********************************************************
     * @Method Name : showEmployeeNotFound
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when employee is not found in the database
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showEmployeeNotFound() {
        System.out.println(Constant.EMPLOYEE_NOT_FOUND);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidDateFormat
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message for invalid date format input
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidDateFormat() {
        System.out.println(Constant.INVALID_DATE_FORMAT);
    }

    /*
     *********************************************************
     * @Method Name : showEndDateBeforeStartDate
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays error message when end date is before start date
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showEndDateBeforeStartDate() {
        System.out.println(Constant.END_BEFORE_START);
    }

    /*
     *********************************************************
     * @Method Name : showNoAttendanceInRange
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when no attendance records are found within a
     * specified date range
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoAttendanceInRange() {
        System.out.println(Constant.NO_ATTENDANCE_IN_RANGE);
    }

    /*
     *********************************************************
     * @Method Name : displayWorkingLog
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays working log summary including daily attendance and
     * total hours worked
     * 
     * @param : String firstName - employee first name
     * : String lastName - employee last name
     * : List<AttendanceModel> logs - attendance records list
     * : double totalHours - total hours worked in the period
     * 
     * @return : void
     ********************************************************
     */
    public static void displayWorkingLog(String firstName, String lastName, List<AttendanceModel> logs,
            double totalHours) {
        System.out.println("\nWorking log for " + firstName + " " + lastName);
        System.out.println("---------------------------------------------");
        for (AttendanceModel log : logs) {
            String date = log.getDate().toString();
            String inTime = log.getInTime() != null ? log.getInTime().toString() : "N/A";
            String outTime = log.getOutTime() != null ? log.getOutTime().toString() : "N/A";
            String status = log.getStatus();
            System.out.printf("%s | In: %s | Out: %s | Status: %s\n", date, inTime, outTime, status);
        }
        System.out.println("Total Hours Worked: " + totalHours);
    }

    /*
     *********************************************************
     * @Method Name : showClockInSuccess
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays success message after successful clock-in
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showClockInSuccess() {
        System.out.println("Clock-in successful.");
    }

    /*
     *********************************************************
     * @Method Name : showAlreadyClockedIn
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message if user tries to clock in more than once in a
     * day
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showAlreadyClockedIn() {
        System.out.println("You have already clocked in today.");
    }

    /*
     *********************************************************
     * @Method Name : showClockOutSuccess
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * * @description : Displays success message after successful clock-out
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showClockOutSuccess() {
        System.out.println("Clock-out successful.");
    }

    /*
     *********************************************************
     * @Method Name : showAlreadyClockedOut
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message if user tries to clock out more than once in
     * a day
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public static void showClockOutFail() {
        System.out.println("You have already clocked out today.");
    }
}
