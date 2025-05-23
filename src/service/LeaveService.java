package service;

import model.LeaveModel;
import repository.LeaveRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LeaveService {
    private LeaveRepo leaveRepo;

    public LeaveService(Connection connection) {
        this.leaveRepo = new LeaveRepo(connection);
    }

    public boolean applyLeave(int empId, String startDate, String endDate) {
        try {
            double currentBalance = leaveRepo.getEmployeeBalance(empId);
            long daysRequested = java.time.temporal.ChronoUnit.DAYS.between(
                    java.time.LocalDate.parse(startDate),
                    java.time.LocalDate.parse(endDate)) + 1;

            if (currentBalance < daysRequested) {
                return false;
            }

            return leaveRepo.applyLeave(empId, startDate, endDate);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to apply leave", e);
        }
    }

    public List<LeaveModel> getLeavesByEmployee(int empId) {
        try {
            return leaveRepo.getLeavesByEmployee(empId);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get leaves by employee", e);
        }
    }

    public List<LeaveModel> getPendingLeavesToApprove(int approverId, String role) {
        try {
            return leaveRepo.getPendingLeavesToApprove(approverId, role);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get pending leaves to approve", e);
        }
    }

    public LeaveApprovalResult processLeaveRequest(int leaveId, int approverId, boolean isApproved) {
        try {
            boolean isProcessed = leaveRepo.processLeaveRequest(leaveId, approverId, isApproved);

            if (isProcessed && isApproved) {
                LeaveModel leave = leaveRepo.getLeaveById(leaveId);
                int empId = leave.getEmpId();
                LocalDate start = leave.getStartDate();
                LocalDate end = leave.getEndDate();
                long daysTaken = java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;

                boolean balanceUpdated = leaveRepo.updateEmployeeLeaveBalance(empId, daysTaken);

                return new LeaveApprovalResult(true, balanceUpdated, start, end, daysTaken);
            }

            return new LeaveApprovalResult(isProcessed, false, null, null, 0);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to process leave request", e);
        }
    }

    public static class LeaveApprovalResult {
        public final boolean isProcessed;
        public final boolean balanceUpdated;
        public final LocalDate startDate;
        public final LocalDate endDate;
        public final long daysTaken;

        public LeaveApprovalResult(boolean isProcessed, boolean balanceUpdated,
                                   LocalDate startDate, LocalDate endDate, long daysTaken) {
            this.isProcessed = isProcessed;
            this.balanceUpdated = balanceUpdated;
            this.startDate = startDate;
            this.endDate = endDate;
            this.daysTaken = daysTaken;
        }
    }
}
