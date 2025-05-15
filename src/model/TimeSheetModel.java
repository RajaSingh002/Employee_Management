package model;

import java.time.LocalDate;

public class TimeSheetModel {

    private int timesheetId;
    private int empId;
    private LocalDate date;
    private double hoursWorked;
    private String description;
    private int companyId;
    private String name;
    private String position;

    public TimeSheetModel() {

    };

    public TimeSheetModel(int empId, double hoursWorked, String description, int companyId) {
        this.empId = empId;
        this.hoursWorked = hoursWorked;
        this.description = description;
        this.companyId = companyId;
        this.date = LocalDate.now();

    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValidHours() {
        return hoursWorked <= 8.0;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
