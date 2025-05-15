package view;

import model.LeaveModel;
import utils.ScannerSingleton;
import utils.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LeaveView {

    private final Scanner sc = ScannerSingleton.getInstance();

    public LeaveModel displayApplyLeaveScreen() {
        LocalDate today = LocalDate.now();

        LocalDate startDate;
        while (true) {
            startDate = getDateInput(Constant.ENTER_START_DATE);
            if (startDate == null)
                return null;

            if (startDate.isBefore(today)) {
                System.out.println(Constant.START_DATE_BEFORE_TODAY);
            } else {
                break;
            }
        }

        LocalDate endDate;
        while (true) {
            endDate = getDateInput(Constant.ENTER_END_DATE);
            if (endDate == null)
                return null;

            if (endDate.isBefore(startDate)) {
                System.out.println(Constant.END_DATE_BEFORE_START);
            } else {
                break;
            }
        }

        LeaveModel leave = new LeaveModel();
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        return leave;
    }

    private LocalDate getDateInput(String label) {
        while (true) {
            System.out.print(label + Constant.BACK_OPTION);
            String input = sc.nextLine().trim().replace("/", "-");

            if (input.equalsIgnoreCase("back"))
                return null;

            try {
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println(Constant.INVALID_DATE_FORMAT);
            }
        }
    }

    public void displayLeaveHistory(List<LeaveModel> leaveHistory) {
        if (leaveHistory.isEmpty()) {
            System.out.println(Constant.NO_LEAVE_HISTORY);
            return;
        }

        for (LeaveModel leave : leaveHistory) {
            System.out.println("Leave ID   : " + leave.getLeaveId());
            System.out.println("Start Date : " + leave.getStartDate());
            System.out.println("End Date   : " + leave.getEndDate());
            System.out.println("Status     : " + leave.getStatus());
            System.out.println(Constant.LEAVE_HISTORY_SEPARATOR);
        }
    }

    public void displayLeaveStatusUpdate(String status) {
        System.out.println(Constant.LEAVE_STATUS_PREFIX + status);
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayPendingLeaves(List<LeaveModel> pendingLeaves) {
        System.out.printf(Constant.LEAVE_HEADER, "Leave ID", "Employee ID", "Start Date", "End Date", "Status");
        System.out.println(Constant.LEAVE_HEADER_LINE);
        for (LeaveModel leave : pendingLeaves) {
            System.out.printf(Constant.LEAVE_ROW,
                    leave.getLeaveId(),
                    leave.getEmpId(),
                    leave.getStartDate(),
                    leave.getEndDate(),
                    leave.getStatus());
        }
    }

    public String getApprovalInput(int leaveId) {
        System.out.printf(Constant.APPROVE_QUESTION, leaveId);
        return sc.nextLine().trim().toLowerCase();
    }
}
