package service;

import model.LeaveModel;
import repository.LeaveRepo;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class LeaveService {
    private LeaveRepo leaveRepo;

    public LeaveService(Connection connection) {
        this.leaveRepo = new LeaveRepo(connection);
    }

    public boolean applyLeave(int empId, String startDate, String endDate) {
        double currentBalance = leaveRepo.getEmployeeBalance(empId);
        long daysRequested = java.time.temporal.ChronoUnit.DAYS.between(
                java.time.LocalDate.parse(startDate), 
                java.time.LocalDate.parse(endDate)) + 1;

        if (currentBalance < daysRequested) {
            return false; 
        }

        return leaveRepo.applyLeave(empId, startDate, endDate);
    }

    public List<LeaveModel> getLeavesByEmployee(int empId) {
        return leaveRepo.getLeavesByEmployee(empId);
    }

    public List<LeaveModel> getPendingLeavesToApprove(int approverId, String role) {
        return leaveRepo.getPendingLeavesToApprove(approverId, role);
    }

    public boolean processLeaveRequest(int leaveId, int approverId, boolean isApproved) {
   
        boolean isProcessed = leaveRepo.processLeaveRequest(leaveId, approverId, isApproved);
        
        if (isProcessed && isApproved) {

            LeaveModel leave = leaveRepo.getLeaveById(leaveId);
            int empId = leave.getEmpId();
             LocalDate start = leave.getStartDate();
        LocalDate end = leave.getEndDate();

        System.out.println("Start: " + start + ", End: " + end);
            long daysTaken = java.time.temporal.ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
            
           System.out.println("Days taken: " + daysTaken);
            return leaveRepo.updateEmployeeLeaveBalance(empId, daysTaken);
        }
        
        return isProcessed;
    }
}
