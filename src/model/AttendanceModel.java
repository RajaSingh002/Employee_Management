package model;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 *******************************************************************************************************
 *   @Class Name         :   AttendanceModel
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   This model class holds attendance details of an employee,
 *                           including in-time, out-time, date, and attendance status.
 *******************************************************************************************************
 */
public class AttendanceModel {

    private int attendanceId;
    private int empId;
    private int companyId;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;
    private String status;

    /*
     ***********************************************************************************************
     * @Constructor : AttendanceModel(int empId, int companyId)
     * 
     * @Description : Initializes a new attendance entry with current date, in-time
     * and default status PRESENT.
     ***********************************************************************************************
     */
    public AttendanceModel(int empId, int companyId) {
        this.empId = empId;
        this.companyId = companyId;
        this.date = LocalDate.now();
        this.inTime = LocalTime.now();
        this.status = "PRESENT";
    }

    /*
     ***********************************************************************************************
     * @Constructor : AttendanceModel()
     * 
     * @Description : Default no-argument constructor.
     ***********************************************************************************************
     */
    public AttendanceModel() {
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalTime inTime) {
        this.inTime = inTime;
    }

    public LocalTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalTime outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*
     ***********************************************************************************************
     * @Method Name : clockOut
     * 
     * @Description : Sets the outTime to current time and calculates hours worked.
     * If hours >= 8, sets status to PRESENT; else ABSENT.
     ***********************************************************************************************
     */
    public void clockOut() {
        this.outTime = LocalTime.now();
        long hoursWorked = java.time.Duration.between(inTime, outTime).toHours();
        this.status = (hoursWorked >= 8) ? "PRESENT" : "ABSENT";
    }
}
