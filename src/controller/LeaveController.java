package controller;

import service.LeaveService;
import service.LeaveService.LeaveApprovalResult;
import utils.Constant;
import model.LeaveModel;
import view.LeaveView;
import model.EmployeeModel;

import java.time.LocalDate;
import java.util.List;

public class LeaveController {
    private final LeaveService leaveService;
    private final LeaveView leaveView;
    private final EmployeeModel user;

    public LeaveController(EmployeeModel user, LeaveService leaveService) {
        this.user = user;
        this.leaveService = leaveService;
        this.leaveView = new LeaveView();
    }

    public void applyLeave() {
        LeaveModel leave = leaveView.displayApplyLeaveScreen();
        if (leave == null) {
            leaveView.displayMessage(Constant.LEAVE_APPLY_CANCELLED);
            return;
        }

        leave.setEmpId(user.getId());

        if (checkForLeaveOverlap(user.getId(), leave.getStartDate(), leave.getEndDate())) {
            leaveView.displayMessage(Constant.LEAVE_OVERLAP);
            return;
        }

        boolean isSuccess = leaveService.applyLeave(
                user.getId(),
                leave.getStartDate().toString(),
                leave.getEndDate().toString()
        );

        leaveView.displayMessage(
                isSuccess ? Constant.LEAVE_APPLIED_SUCCESSFULLY : Constant.LEAVE_APPLICATION_FAILED
        );
    }

    private boolean checkForLeaveOverlap(int empId, LocalDate startDate, LocalDate endDate) {
        List<LeaveModel> approvedLeaves = leaveService.getLeavesByEmployee(empId);

        for (LeaveModel leave : approvedLeaves) {
            if (startDate.isBefore(leave.getEndDate()) && endDate.isAfter(leave.getStartDate())) {
                return true;
            }
        }
        return false;
    }

    public void approveLeave() {
        List<LeaveModel> pendingLeaves = leaveService.getPendingLeavesToApprove(user.getId(), user.getPosition());

        if (pendingLeaves.isEmpty()) {
            leaveView.displayMessage(Constant.NO_LEAVE_REQUESTS);
            return;
        }

        leaveView.displayPendingLeaves(pendingLeaves);

        for (LeaveModel leave : pendingLeaves) {
            while (true) {
                String input = leaveView.getApprovalInput(leave.getLeaveId());

                if ("yes".equalsIgnoreCase(input)) {
                    LeaveApprovalResult result = leaveService.processLeaveRequest(leave.getLeaveId(), user.getId(), true);
                    leaveView.displayLeaveApprovalResult(result);
                    break;
                } else if ("no".equalsIgnoreCase(input)) {
                    boolean rejected = leaveService.processLeaveRequest(leave.getLeaveId(), user.getId(), false).isProcessed;
                    leaveView.displayLeaveStatusUpdate(
                            rejected ? Constant.LEAVE_REJECTED_SUCCESS : Constant.LEAVE_STATUS_UPDATE_FAILED
                    );
                    break;
                } else {
                    leaveView.displayMessage(Constant.INVALID_APPROVAL_INPUT);
                }
            }
        }
    }
}
