/*
*********************************************************
 *  @Class Name     : AttendanceRepo
 *  @author         : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company        : Antrazal
 *  @description    : Repository class for handling attendance database operations
*********************************************************
*/

package repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.AttendanceModel;
import utils.Constant;

public class AttendanceRepo {

    private Connection conn;

    public enum AttendanceStatus {
        IN_PROGRESS, PRESENT, ABSENT;
    }

    /*
     *********************************************************
     * @Method Name : AttendanceRepo (Constructor)
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Initializes AttendanceRepo with database connection
     * 
     * @param : Connection conn
     * 
     * @return : N/A
     *********************************************************
     */
    public AttendanceRepo(Connection conn) {
        this.conn = conn;
    }

    /*
     *********************************************************
     * @Method Name : markInTime
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Inserts a new attendance record marking employee in-time
     * 
     * @param : AttendanceModel attendance
     * 
     * @return : boolean true if insert successful, false otherwise
     * 
     * @throws : SQLException
     *********************************************************
     */
    public boolean markInTime(AttendanceModel attendance) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.INSERT_IN_TIME_SQL)) {
            stmt.setInt(1, attendance.getEmpId());
            stmt.setInt(2, attendance.getCompanyId());
            stmt.setDate(3, Date.valueOf(attendance.getDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(attendance.getInTime().atDate(LocalDate.now())));
            stmt.setString(5, AttendanceStatus.IN_PROGRESS.name());
            return stmt.executeUpdate() > 0;
        }
    }

    /*
     *********************************************************
     * @Method Name : getTodayAttendance
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Retrieves today's attendance record for given employee and
     * company
     * 
     * @param : int empId, int companyId
     * 
     * @return : AttendanceModel if found, otherwise null
     * 
     * @throws : SQLException
     *********************************************************
     */
    public AttendanceModel getTodayAttendance(int empId, int companyId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_TODAY_ATTENDANCE_SQL)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AttendanceModel model = new AttendanceModel(empId, companyId);
                model.setAttendanceId(rs.getInt("attendance_id"));
                model.setDate(rs.getDate("date").toLocalDate());

                Timestamp inTimestamp = rs.getTimestamp("in_time");
                if (inTimestamp != null) {
                    model.setInTime(inTimestamp.toLocalDateTime().toLocalTime());
                }

                Timestamp outTimestamp = rs.getTimestamp("out_time");
                if (outTimestamp != null) {
                    model.setOutTime(outTimestamp.toLocalDateTime().toLocalTime());
                }

                model.setStatus(rs.getString("status"));
                return model;
            }
            return null;
        }
    }

    /*
     *********************************************************
     * @Method Name : updateOutTimeAndStatus
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Updates the out-time and attendance status for an employee on
     * a specific date
     * 
     * @param : AttendanceModel model
     * 
     * @return : boolean true if update successful, false otherwise
     * 
     * @throws : SQLException
     * 
     * @throws : SQLException if out-time already marked
     *********************************************************
     */
    public boolean updateOutTimeAndStatus(AttendanceModel model) throws SQLException {
        try (PreparedStatement checkStmt = conn.prepareStatement(Constant.SELECT_OUT_TIME_SQL)) {
            checkStmt.setInt(1, model.getEmpId());
            checkStmt.setInt(2, model.getCompanyId());
            checkStmt.setDate(3, Date.valueOf(model.getDate()));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                Timestamp outTimestamp = rs.getTimestamp("out_time");
                if (outTimestamp != null) {
                    throw new SQLException("Out time already marked for today.");
                }
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(Constant.UPDATE_OUT_TIME_SQL)) {
                updateStmt.setTimestamp(1, Timestamp.valueOf(model.getOutTime().atDate(LocalDate.now())));
                updateStmt.setString(2, model.getStatus());
                updateStmt.setInt(3, model.getEmpId());
                updateStmt.setInt(4, model.getCompanyId());
                updateStmt.setDate(5, Date.valueOf(model.getDate()));
                return updateStmt.executeUpdate() > 0;
            }
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
     * @description : Retrieves attendance records for the last 30 days for a
     * company
     * 
     * @param : int companyId
     * 
     * @return : List<AttendanceModel> attendance records
     * 
     * @throws : SQLException
     *********************************************************
     */
    public List<AttendanceModel> getAttendanceHistory(int companyId) throws SQLException {
        List<AttendanceModel> attendanceList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_ATTENDANCE_HISTORY_SQL)) {
            stmt.setInt(1, companyId);
            stmt.setDate(2, Date.valueOf(LocalDate.now().minusDays(30)));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AttendanceModel model = new AttendanceModel(rs.getInt("emp_id"), companyId);
                model.setAttendanceId(rs.getInt("attendance_id"));
                model.setDate(rs.getDate("date").toLocalDate());

                Timestamp inTimestamp = rs.getTimestamp("in_time");
                if (inTimestamp != null) {
                    model.setInTime(inTimestamp.toLocalDateTime().toLocalTime());
                }

                Timestamp outTimestamp = rs.getTimestamp("out_time");
                if (outTimestamp != null) {
                    model.setOutTime(outTimestamp.toLocalDateTime().toLocalTime());
                }

                model.setStatus(rs.getString("status"));
                attendanceList.add(model);
            }
        }
        return attendanceList;
    }

    /*
     *********************************************************
     * @Method Name : getPresentDaysInCurrentMonth
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Counts the number of days the employee was present in the
     * current month
     * 
     * @param : int empId, int companyId
     * 
     * @return : int count of present days
     * 
     * @throws : SQLException
     *********************************************************
     */
    public int getPresentDaysInCurrentMonth(int empId, int companyId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_PRESENT_DAYS_CURRENT_MONTH_SQL)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
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
     * @description : Retrieves attendance records for an employee between two dates
     * 
     * @param : int empId, LocalDate startDate, LocalDate endDate
     * 
     * @return : List<AttendanceModel> attendance records
     * 
     * @throws : SQLException
     *********************************************************
     */
    public List<AttendanceModel> getAttendanceBetweenDates(int empId, LocalDate startDate, LocalDate endDate)
            throws SQLException {
        List<AttendanceModel> attendanceList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_ATTENDANCE_BETWEEN_DATES_SQL)) {
            stmt.setInt(1, empId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AttendanceModel attendance = new AttendanceModel();
                attendance.setEmpId(rs.getInt("emp_id"));
                attendance.setDate(rs.getDate("date").toLocalDate());
                attendance.setInTime(rs.getTimestamp("in_time").toLocalDateTime().toLocalTime());
                attendance.setOutTime(rs.getTimestamp("out_time").toLocalDateTime().toLocalTime());
                attendanceList.add(attendance);
            }
        }
        return attendanceList;
    }
}
