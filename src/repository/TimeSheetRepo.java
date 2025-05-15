package repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.TimeSheetModel;
import utils.Constant;

public class TimeSheetRepo {
    private Connection conn;

    public TimeSheetRepo(Connection conn) {
        this.conn = conn;
    }

    public boolean addTimesheet(TimeSheetModel ts) {
        try (PreparedStatement stmt = conn.prepareStatement(Constant.INSERT_TIMESHEET)) {
            stmt.setInt(1, ts.getEmpId());
            stmt.setInt(2, ts.getCompanyId());
            stmt.setDate(3, Date.valueOf(ts.getDate()));
            stmt.setString(4, ts.getDescription());
            stmt.setDouble(5, ts.getHoursWorked());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TimeSheetModel> getAllTimesheetsWithEmployeeInfo(int companyId) {
        List<TimeSheetModel> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(Constant.SELECT_ALL_TIMESHEETS_WITH_EMPLOYEE_INFO)) {
            stmt.setInt(1, companyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeSheetModel ts = new TimeSheetModel();
                ts.setTimesheetId(rs.getInt("timesheet_id"));
                ts.setEmpId(rs.getInt("emp_id"));
                ts.setCompanyId(companyId);
                ts.setDate(rs.getDate("date").toLocalDate());
                ts.setDescription(rs.getString("task_description"));
                ts.setHoursWorked(rs.getDouble("hours_worked"));
                ts.setName(rs.getString("first_name"));
                ts.setPosition(rs.getString("position"));
                list.add(ts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean existsByDate(int empId, int companyId, LocalDate date) {
        try (PreparedStatement ps = conn.prepareStatement(Constant.CHECK_TIMESHEET_EXISTS_BY_DATE)) {
            ps.setInt(1, empId);
            ps.setInt(2, companyId);
            ps.setDate(3, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalHoursForDate(int empId, int companyId, LocalDate date) {
        double total = 0.0;
        try (PreparedStatement stmt = conn.prepareStatement(Constant.GET_TOTAL_HOURS_FOR_DATE)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            stmt.setDate(3, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total_hours");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
