package controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import model.AttendanceModel;
import model.EmployeeModel;
import service.AttendanceService;
import service.EmployeeService;
import utils.Constant;
import utils.ScannerSingleton;
import view.AttendanceView;

public class AttendanceController {
    protected final EmployeeModel user;
    protected final AttendanceService aService;
    private final EmployeeService eService;

    public AttendanceController(EmployeeModel user, Connection conn) {
        this.user = user;
        this.aService = new AttendanceService(conn);
        this.eService = new EmployeeService(conn);
    }

    public void showTodayAttendance() {
        AttendanceModel today = aService.getTodayAttendance(user.getId(), user.getCompanyId());

        if (today == null || today.getInTime() == null) {
            AttendanceView.showNoAttendanceForToday();
            return;
        }

        AttendanceView.displayTodayAttendance(today);
    }

    public void showAttendanceHistory() {
        List<AttendanceModel> history = aService.getAttendanceHistory(user.getCompanyId());

        if (history == null || history.isEmpty()) {
            AttendanceView.showNoAttendanceHistory();
            return;
        }

        AttendanceView.displayAttendanceHistory(history);
    }

    public void showWorkingLogReport() {
        var sc = ScannerSingleton.getInstance();

        AttendanceView.EmployeeId();
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase(Constant.BACK)) return;

        int empId;
        try {
            empId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            AttendanceView.showInvalidEmployeeId();
            return;
        }

        EmployeeModel emp = eService.getActiveEmployeeById(empId, user.getCompanyId());
        if (emp == null || emp.getCompanyId() != user.getCompanyId()) {
            AttendanceView.showEmployeeNotFound();
            return;
        }

        LocalDate startDate;
        while (true) {
            AttendanceView.StartDate();
            String fromInput = sc.nextLine().trim();
            if (fromInput.equalsIgnoreCase(Constant.BACK)) return;
            try {
                startDate = LocalDate.parse(fromInput);
                break;
            } catch (Exception e) {
                AttendanceView.showInvalidDateFormat();
            }
        }

        LocalDate endDate;
        while (true) {
            AttendanceView.EndDate();
            String toInput = sc.nextLine().trim();
            if (toInput.equalsIgnoreCase(Constant.BACK)) return;
            try {
                endDate = LocalDate.parse(toInput);
                if (endDate.isBefore(startDate)) {
                    AttendanceView.showEndDateBeforeStartDate();
                } else {
                    break;
                }
            } catch (Exception e) {
                AttendanceView.showInvalidDateFormat();
            }
        }

        List<AttendanceModel> logs = aService.getAttendanceBetweenDates(empId, startDate, endDate);
        if (logs.isEmpty()) {
            AttendanceView.showNoAttendanceInRange();
            return;
        }

        double totalHours = aService.calculateWorkingHours(logs);
        AttendanceView.displayWorkingLog(emp.getFirstName(), emp.getLastName(), logs, totalHours);
    }

    public void clockIn() {
        boolean success = aService.clockIn(user.getId(), user.getCompanyId());
        if (success) {
           
            AttendanceView.showClockInSuccess();
        } else {

            AttendanceView.showAlreadyClockedIn();
        }
    }

    public void clockOut() {
        boolean success = aService.clockOut(user.getId(), user.getCompanyId());
        if (success) {
            AttendanceView.showClockOutSuccess();
        } else {
            AttendanceView.showClockOutFail();
        }
    }
}
