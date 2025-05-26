

package service;

import model.LeaveModel;
import repository.LeaveRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/*
*********************************************************
 *  @Class Name     : LeaveService
 *  @author         : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company        : Antrazal
 *  @description    : Contains business logic related to leave
 *                    application, approval, and balance management
*********************************************************
 */

public class LeaveService {

    private final LeaveRepo leaveRepo;

    /*
    *********************************************************
     *  @Method Name    : LeaveService (Constructor)
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Initializes the LeaveRepo with DB connection
     *  @param          : Connection connection
     *  @return         : N/A
    *********************************************************
    */
    public LeaveService(Connection connection) {
        this.leaveRepo = new LeaveRepo(connection);
    }

    /*
    *********************************************************
     *  @Method Name    : applyLeave
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Apply for leave after checking balance
     *  @param          : int empId, String startDate, String endDate
     *  @return         : boolean (true if applied successfully)
    *********************************************************
    */
    public boolean applyLeave(int empId, String startDate, String endDate) {
        try {
            double currentBalance = leaveRepo.getEmployeeBalance(empId);
            long daysRequested = java.time.temporal.ChronoUnit.DAYS.between(
                    LocalDate.parse(startDate), LocalDate.parse(endDate)) + 1;

            if (currentBalance < daysRequested) {
                return false;
            }

            return leaveRepo.applyLeave(empId, startDate, endDate);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to apply leave", e);
        }
    }

    /*
    *********************************************************
     *  @Method Name    : getLeavesByEmployee
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Get all leave records of an employee
     *  @param          : int empId
     *  @return         : List<LeaveModel>
    *********************************************************
    */
    public List<LeaveModel> getLeavesByEmployee(int empId) {
        try {
            return leaveRepo.getLeavesByEmployee(empId);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get leaves by employee", e);
        }
    }

    /*
    *********************************************************
     *  @Method Name    : getPendingLeavesToApprove
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Get pending leaves assigned to a specific approver
     *  @param          : int approverId, String role
     *  @return         : List<LeaveModel>
    *********************************************************
    */
    public List<LeaveModel> getPendingLeavesToApprove(int approverId, String role) {
        try {
            return leaveRepo.getPendingLeavesToApprove(approverId, role);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get pending leaves to approve", e);
        }
    }

    /*
    *********************************************************
     *  @Method Name    : processLeaveRequest
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Approve or reject a pending leave and update balance if needed
     *  @param          : int leaveId, int approverId, boolean isApproved
     *  @return         : LeaveApprovalResult
    *********************************************************
    */
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

    /*
    *********************************************************
     *  @Class Name     : LeaveApprovalResult
     *  @author         : Raja Kumar (raja.kumar@antrazal.com)
     *  @Company        : Antrazal
     *  @description    : Encapsulates result of leave approval process
    *********************************************************
    */
    public static class LeaveApprovalResult {
        public final boolean isProcessed;
        public final boolean balanceUpdated;
        public final LocalDate startDate;
        public final LocalDate endDate;
        public final long daysTaken;

        /*
        *********************************************************
         *  @Constructor    : LeaveApprovalResult
         *  @author         : Raja Kumar (raja.kumar@antrazal.com)
         *  @Company        : Antrazal
         *  @description    : Initializes approval result fields
         *  @param          : boolean isProcessed, boolean balanceUpdated,
         *                    LocalDate startDate, LocalDate endDate, long daysTaken
        *********************************************************
        */
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
