package service;

import model.AttendanceModel;
import repository.AttendanceRepo;
import utils.Constant;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class AttendanceService {

    private AttendanceRepo attendanceRepo;

    public AttendanceService(Connection conn) {
        this.attendanceRepo = new AttendanceRepo(conn);
    }

    public boolean clockIn(int empId, int companyId) {

        AttendanceModel existingAttendance = attendanceRepo.getTodayAttendance(empId, companyId);

        if (existingAttendance != null) {
            System.out.println(Constant.CLOCKED_IN);
            return false;
        } else {
            System.out.println();
        }

        AttendanceModel attendance = new AttendanceModel(empId, companyId);
        return attendanceRepo.markInTime(attendance);
    }

    public boolean clockOut(int empId, int companyId) {
        AttendanceModel attendance = attendanceRepo.getTodayAttendance(empId, companyId);

        if (attendance == null) {
            System.out.println(Constant.CHECK_CLOCKED);
            return false;
        } else {
            System.out.println(Constant.CLOCKED_IN_SUCCESS);
        }

        attendance.clockOut();
        attendance.setStatus(calculateStatus(attendance));

        return attendanceRepo.updateOutTimeAndStatus(attendance);
    }

    private String calculateStatus(AttendanceModel attendance) {
        if (attendance.getOutTime() != null && attendance.getInTime() != null) {

            Duration duration = Duration.between(attendance.getInTime(), attendance.getOutTime());
            long hoursWorked = duration.toHours();

            return (hoursWorked >= 8) ? "PRESENT" : "ABSENT";
        }
        return "ABSENT";
    }

    public AttendanceModel getTodayAttendance(int empId, int companyId) {
        return attendanceRepo.getTodayAttendance(empId, companyId);
    }

    public List<AttendanceModel> getAttendanceHistory(int companyId) {
        return attendanceRepo.getAttendanceHistory(companyId);
    }

    public int getPresentDays(int empId, int companyId) {
        return attendanceRepo.getPresentDaysInCurrentMonth(empId, companyId);
    }

    public List<AttendanceModel> getAttendanceBetweenDates(int empId, LocalDate start, LocalDate end) {
        return attendanceRepo.getAttendanceBetweenDates(empId, start, end);
    }

    public double calculateWorkingHours(List<AttendanceModel> logs) {
        double total = 0;
        for (AttendanceModel log : logs) {
            if (log.getInTime() != null && log.getOutTime() != null) {
                Duration duration = Duration.between(log.getInTime(), log.getOutTime());
                total += duration.toMinutes() / 60.0;
            }
        }
        return total;
    }

}
