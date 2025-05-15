package controller;

import service.LeaveService;
import utils.Constant;
import model.LeaveModel;
import view.LeaveView;
import model.EmployeeModel;

import java.util.List;
import java.time.*;

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

        boolean isSuccess = leaveService.applyLeave(user.getId(),
                leave.getStartDate().toString(), leave.getEndDate().toString());

        if (isSuccess) {
            leaveView.displayMessage(Constant.LEAVE_APPLIED_SUCCESSFULLY);
        } else {
            leaveView.displayMessage(Constant.LEAVE_APPLICATION_FAILED );
        }
    }

    private boolean checkForLeaveOverlap(int empId, LocalDate startDate, LocalDate endDate) {
        List<LeaveModel> approvedLeaves = leaveService.getLeavesByEmployee(empId);

        for (LeaveModel leave : approvedLeaves) {
            if ((startDate.isBefore(leave.getEndDate()) && endDate.isAfter(leave.getStartDate()))) {
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
            String input = "";
            while (true) {
                input = leaveView.getApprovalInput(leave.getLeaveId());
                if ("yes".equals(input)) {
                    boolean success = leaveService.processLeaveRequest(leave.getLeaveId(), user.getId(), true);
                    leaveView.displayLeaveStatusUpdate(success ? Constant.LEAVE_APPROVED_SUCCESS
                            : Constant.LEAVE_STATUS_UPDATE_FAILED );
                    break;
                } else if ("no".equals(input)) {
                    boolean success = leaveService.processLeaveRequest(leave.getLeaveId(), user.getId(), false);
                    leaveView.displayLeaveStatusUpdate(success ? Constant.LEAVE_REJECTED_SUCCESS
                            : Constant.LEAVE_STATUS_UPDATE_FAILED);
                    break;
                } else {
                    leaveView.displayMessage(Constant.INVALID_APPROVAL_INPUT);
                }
            }
        }
    }
}
