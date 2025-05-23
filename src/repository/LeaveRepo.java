package repository;

import model.LeaveModel;
import utils.Constant;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LeaveRepo {
    private Connection connection;

    public LeaveRepo(Connection connection) {
        this.connection = connection;
    }

    public boolean applyLeave(int empId, String startDate, String endDate) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(Constant.INSERT_LEAVE_REQUEST)) {
            ps.setInt(1, empId);
            ps.setDate(2, Date.valueOf(LocalDate.parse(startDate)));
            ps.setDate(3, Date.valueOf(LocalDate.parse(endDate)));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to apply for leave", e);
        }
    }

    public List<LeaveModel> getLeavesByEmployee(int empId) throws SQLException  {
        List<LeaveModel> leaves = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(Constant.SELECT_LEAVES_BY_EMPLOYEE)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LeaveModel leave = new LeaveModel();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmpId(rs.getInt("emp_id"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setStatus(rs.getString("status"));
                int approver = rs.getInt("approved_by");
                leave.setApprovedBy(rs.wasNull() ? null : approver);
                leaves.add(leave);
            }
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to retrieve leaves for employee ID: " + empId, e);
        }
        return leaves;
    }

    public List<LeaveModel> getPendingLeavesToApprove(int approverId, String role) throws SQLException {
        List<LeaveModel> leaves = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(Constant.SELECT_PENDING_LEAVES_TO_APPROVE)) {
            ps.setString(1, role);
            ps.setInt(2, approverId);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LeaveModel leave = new LeaveModel();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmpId(rs.getInt("emp_id"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setStatus(rs.getString("status"));
                int approver = rs.getInt("approved_by");
                leave.setApprovedBy(rs.wasNull() ? null : approver);
                leaves.add(leave);
            }
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to fetch pending leaves for approval", e);
        }
        return leaves;
    }

    public boolean processLeaveRequest(int leaveId, int approverId, boolean isApproved)throws SQLException  {
        String status = isApproved ? "APPROVED" : "REJECTED";
        try (PreparedStatement ps = connection.prepareStatement(Constant.UPDATE_LEAVE_STATUS)) {
            ps.setString(1, status);
            ps.setInt(2, approverId);
            ps.setInt(3, leaveId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to process leave request for leave ID: " + leaveId, e);
        }
    }

    public double getEmployeeBalance(int empId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(Constant.SELECT_EMPLOYEE_BALANCE)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("leaveBalance");
            }
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to fetch leave balance for employee ID: " + empId, e);
        }
        return 0;
    }

    public boolean updateEmployeeLeaveBalance(int empId, long daysTaken) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(Constant.UPDATE_EMPLOYEE_BALANCE)) {
            ps.setLong(1, daysTaken);
            ps.setInt(2, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to update leave balance for employee ID: " + empId, e);
        }
    }

    public LeaveModel getLeaveById(int leaveId)throws SQLException  {
        try (PreparedStatement ps = connection.prepareStatement(Constant.SELECT_LEAVE_BY_ID)) {
            ps.setInt(1, leaveId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LeaveModel leave = new LeaveModel();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmpId(rs.getInt("emp_id"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setStatus(rs.getString("status"));
                return leave;
            }
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to fetch leave by ID: " + leaveId, e);
        }
        return null;
    }

    public long countApprovedLeavesInMonth(int empId, int year, int month) throws SQLException {
        long days = 0;
        try (PreparedStatement ps = connection.prepareStatement(Constant.COUNT_APPROVED_LEAVES_IN_MONTH)) {
            ps.setInt(1, empId);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate start = rs.getDate("start_date").toLocalDate();
                LocalDate end = rs.getDate("end_date").toLocalDate();
                days += ChronoUnit.DAYS.between(start, end) + 1;
            }
        } catch (SQLException e) {
            throw new LeaveRepoException("Failed to count approved leaves in month", e);
        }
        return days;
    }

    public static class LeaveRepoException extends RuntimeException {
        public LeaveRepoException(String message) {
            super(message);
        }

        public LeaveRepoException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
