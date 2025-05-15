package repository;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import model.AttendanceModel;
import utils.Constant;

public class AttendanceRepo {
    private Connection conn;

    public enum AttendanceStatus {
        IN_PROGRESS, PRESENT, ABSENT;
    }

    public AttendanceRepo(Connection conn) {
        this.conn = conn;
    }

   
  

    public boolean markInTime(AttendanceModel attendance) {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.INSERT_IN_TIME_SQL)) {
            stmt.setInt(1, attendance.getEmpId());
            stmt.setInt(2, attendance.getCompanyId());
            stmt.setDate(3, Date.valueOf(attendance.getDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(attendance.getInTime().atDate(LocalDate.now())));
            stmt.setString(5, AttendanceStatus.IN_PROGRESS.name());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(Constant.CLOCKED_IN);
            e.printStackTrace();
            return false;
        }
    }

    public AttendanceModel getTodayAttendance(int empId, int companyId) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateOutTimeAndStatus(AttendanceModel model) {
        try (PreparedStatement checkStmt = conn.prepareStatement(Constant.SELECT_OUT_TIME_SQL)) {
            checkStmt.setInt(1, model.getEmpId());
            checkStmt.setInt(2, model.getCompanyId());
            checkStmt.setDate(3, Date.valueOf(model.getDate()));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                Timestamp outTimestamp = rs.getTimestamp("out_time");
                if (outTimestamp != null) {
                    System.out.println(Constant.CLOCKED_OUT);
                    return false;
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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AttendanceModel> getAttendanceHistory(int companyId) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }

    public int getPresentDaysInCurrentMonth(int empId, int companyId) {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_PRESENT_DAYS_CURRENT_MONTH_SQL)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<AttendanceModel> getAttendanceBetweenDates(int empId, LocalDate startDate, LocalDate endDate) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
}
