package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LeaveModel {

    private int leaveId;
    private int empId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Integer approvedBy;

    public LeaveModel() {

    }

    public LeaveModel(int empId, LocalDate startDate, LocalDate endDate) {
        this.empId = empId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PENDING";

    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getDurationInDays() {
        return (int) startDate.until(endDate).getDays() + 1;
    }

    public long getLeaveDuration() {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

}
