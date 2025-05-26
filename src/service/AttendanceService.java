package service;

import model.AttendanceModel;
import repository.AttendanceRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/*
 *********************************************************************************************************
 *  @Class Name      : AttendanceService
 *  @author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 12-05-2025
 *  @Description     : Service class responsible for handling business logic related to
 *                     employee attendance such as clock-in, clock-out, attendance history,
 *                     and working hours calculation.
 *********************************************************************************************************
 */
public class AttendanceService {

    private AttendanceRepo attendanceRepo;

    /*
     *********************************************************
     * @Method Name : AttendanceService
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Constructor initializing AttendanceRepo.
     * 
     * @param : conn - SQL Connection object
     * 
     * @return : -
     ********************************************************
     */
    public AttendanceService(Connection conn) {
        this.attendanceRepo = new AttendanceRepo(conn);
    }

    /*
     *********************************************************
     * @Method Name : clockIn
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Records clock-in if not already marked for today.
     * 
     * @param : empId, companyId
     * 
     * @return : boolean - true if successful, false otherwise
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : clockOut
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Records clock-out and calculates attendance status.
     * 
     * @param : empId, companyId
     * 
     * @return : boolean - true if successful, false otherwise
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : calculateStatus
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Calculates if the employee is present or absent.
     * 
     * @param : attendance - AttendanceModel
     * 
     * @return : String - "PRESENT" or "ABSENT"
     ********************************************************
     */
    private String calculateStatus(AttendanceModel attendance) {
        if (attendance.getOutTime() != null && attendance.getInTime() != null) {
            Duration duration = Duration.between(attendance.getInTime(), attendance.getOutTime());
            long hoursWorked = duration.toHours();
            return (hoursWorked >= 8) ? "PRESENT" : "ABSENT";
        }
        return "ABSENT";
    }

    /*
     *********************************************************
     * @Method Name : getTodayAttendance
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Fetches today's attendance record.
     * 
     * @param : empId, companyId
     * 
     * @return : AttendanceModel or null
     ********************************************************
     */
    public AttendanceModel getTodayAttendance(int empId, int companyId) {
        try {
            return attendanceRepo.getTodayAttendance(empId, companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     *********************************************************
     * @Method Name : getAttendanceHistory
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Returns entire company attendance history.
     * 
     * @param : companyId
     * 
     * @return : List<AttendanceModel>
     ********************************************************
     */
    public List<AttendanceModel> getAttendanceHistory(int companyId) {
        try {
            return attendanceRepo.getAttendanceHistory(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*
     *********************************************************
     * @Method Name : getPresentDays
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Returns number of present days in current month.
     * 
     * @param : empId, companyId
     * 
     * @return : int - present day count
     ********************************************************
     */
    public int getPresentDays(int empId, int companyId) {
        try {
            return attendanceRepo.getPresentDaysInCurrentMonth(empId, companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     *********************************************************
     * @Method Name : getAttendanceBetweenDates
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Fetches attendance for a specific date range.
     * 
     * @param : empId, start, end
     * 
     * @return : List<AttendanceModel>
     ********************************************************
     */
    public List<AttendanceModel> getAttendanceBetweenDates(int empId, LocalDate start, LocalDate end) {
        try {
            return attendanceRepo.getAttendanceBetweenDates(empId, start, end);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*
     *********************************************************
     * @Method Name : calculateWorkingHours
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Calculates total working hours from attendance logs.
     * 
     * @param : logs - List<AttendanceModel>
     * 
     * @return : double - total hours worked
     ********************************************************
     */
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
