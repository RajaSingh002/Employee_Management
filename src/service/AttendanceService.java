package service;

import model.AttendanceModel;
import repository.AttendanceRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AttendanceService {

    private AttendanceRepo attendanceRepo;

    public AttendanceService(Connection conn) {
        this.attendanceRepo = new AttendanceRepo(conn);
    }

    public boolean clockIn(int empId, int companyId) {
        try {
            AttendanceModel existingAttendance = attendanceRepo.getTodayAttendance(empId, companyId);
            if (existingAttendance != null) {
                return false;
            }
            AttendanceModel attendance = new AttendanceModel(empId, companyId);
            return attendanceRepo.markInTime(attendance);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clockOut(int empId, int companyId) {
        try {
            AttendanceModel attendance = attendanceRepo.getTodayAttendance(empId, companyId);
            if (attendance == null) {
                return false;
            }

            attendance.clockOut();
            attendance.setStatus(calculateStatus(attendance));
            return attendanceRepo.updateOutTimeAndStatus(attendance);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        try {
            return attendanceRepo.getTodayAttendance(empId, companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AttendanceModel> getAttendanceHistory(int companyId) {
        try {
            return attendanceRepo.getAttendanceHistory(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getPresentDays(int empId, int companyId) {
        try {
            return attendanceRepo.getPresentDaysInCurrentMonth(empId, companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<AttendanceModel> getAttendanceBetweenDates(int empId, LocalDate start, LocalDate end) {
        try {
            return attendanceRepo.getAttendanceBetweenDates(empId, start, end);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
