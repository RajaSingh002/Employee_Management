package view;

import model.LeaveModel;
import service.LeaveService;
import utils.ScannerSingleton;
import utils.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LeaveView {

    private final Scanner sc = ScannerSingleton.getInstance();

    /*
     *********************************************************
     * @Method Name : displayApplyLeaveScreen
     * 
     * @description : Interactively prompts user to enter valid start and end dates
     * for leave application.
     * Validates dates against today's date and logical ordering.
     * 
     * @param : none
     * 
     * @return : LeaveModel with startDate and endDate set, or null if user cancels
     * ('back')
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : getDateInput
     * 
     * @description : Helper method to get a date input from user with prompt.
     * Accepts input in ISO_LOCAL_DATE format or user can type 'back' to cancel.
     * Replaces '/' with '-' for user convenience.
     * 
     * @param : String label - prompt to show
     * 
     * @return : LocalDate parsed from input, or null if 'back' is entered
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : displayLeaveHistory
     * 
     * @description : Displays the leave history of an employee with details.
     * 
     * @param : List<LeaveModel> leaveHistory - list of leave records
     * 
     * @return : void
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : displayLeaveStatusUpdate
     * 
     * @description : Displays status message related to leave actions.
     * 
     * @param : String status - status message to display
     ********************************************************
     */
    public void displayLeaveStatusUpdate(String status) {
        System.out.println(Constant.LEAVE_STATUS_PREFIX + status);
    }

    /*
     *********************************************************
     * @Method Name : displayMessage
     * 
     * @description : Displays any generic message to the user.
     * 
     * @param : String msg - message to display
     ********************************************************
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    /*
     *********************************************************
     * @Method Name : displayPendingLeaves
     * 
     * @description : Displays a formatted table of pending leave requests.
     * 
     * @param : List<LeaveModel> pendingLeaves - list of pending leaves to display
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : getApprovalInput
     * 
     * @description : Prompts user to enter approval input (approve/reject) for a
     * leave ID.
     * 
     * @param : int leaveId - the leave request ID for which approval input is
     * needed
     * 
     * @return : String - trimmed lowercase user input
     ********************************************************
     */
    public String getApprovalInput(int leaveId) {
        System.out.printf(Constant.APPROVE_QUESTION, leaveId);
        return sc.nextLine().trim().toLowerCase();
    }

    /*
     *********************************************************
     * @Method Name : displayLeaveApprovalResult
     * 
     * @description : Displays the result of leave approval processing with detailed
     * info.
     * 
     * @param : LeaveService.LeaveApprovalResult result - result object with status
     * flags and info
     ********************************************************
     */
    public void displayLeaveApprovalResult(LeaveService.LeaveApprovalResult result) {
        if (result == null) {
            System.out.println("Error: null result received.");
            return;
        }

        if (result.isProcessed) {
            if (result.balanceUpdated) {
                System.out.println("Leave approved successfully.");
                System.out.println("Start: " + result.startDate + ", End: " + result.endDate);
                System.out.println("Days taken: " + result.daysTaken);
            } else {
                System.out.println("Leave approved but failed to update leave balance.");
            }
        } else {
            System.out.println("Leave request processing failed.");
        }
    }
}
