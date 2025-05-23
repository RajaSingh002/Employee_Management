package view;

import java.util.List;

import model.AttendanceModel;
import utils.Constant;

public class AttendanceView {
    public static void showNoAttendanceForToday() {
        System.out.println(Constant.TODAY_ATTENDANCE);
    }

    public static void displayTodayAttendance(AttendanceModel today) {

        Constant.View1();
        String inTime = today.getInTime() != null ? today.getInTime().toString() : "N/A";
        String outTime = today.getOutTime() != null ? today.getOutTime().toString() : "N/A";
        String status = today.getStatus();

        System.out.printf("| %-19s | %-19s | %-9s |\n", inTime, outTime, status);
        System.out.println(Constant.LINE);
    }

    public static void showNoAttendanceHistory() {
        System.out.println(Constant.NO_ATTENDANCE);
    }

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

    public static void EmployeeId() {
        System.out.print(Constant.EMPLOYEE_ID);
    }

    public static void StartDate() {
        System.out.print(Constant.START_DATE);
    }

    public static void EndDate() {
        System.out.print(Constant.END_DATE);
    }

    public static void showInvalidEmployeeId() {
        System.out.println(Constant.INVALID_EMPLOYEE_ID);
    }

    public static void showEmployeeNotFound() {
        System.out.println(Constant.EMPLOYEE_NOT_FOUND);
    }

    public static void showInvalidDateFormat() {
        System.out.println(Constant.INVALID_DATE_FORMAT);
    }

    public static void showEndDateBeforeStartDate() {
        System.out.println(Constant.END_BEFORE_START);
    }

    public static void showNoAttendanceInRange() {
        System.out.println(Constant.NO_ATTENDANCE_IN_RANGE);
    }

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

    public static void showClockInSuccess() {
        System.out.println("Clock-in successful.");
    }

    public static void showAlreadyClockedIn() {
        System.out.println("You have already clocked in today.");
    }

    public static void showClockOutSuccess() {
        System.out.println("Clock-out successful.");
    }

    public static void showClockOutFail() {
        System.out.println("You have already clocked out today.");
    }

}
