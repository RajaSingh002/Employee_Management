package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import model.TimeSheetModel;
import repository.TimeSheetRepo;

public class TimeSheetService {

    private TimeSheetRepo repo;

    public TimeSheetService(Connection conn) {
        this.repo = new TimeSheetRepo(conn);
    }

    public boolean addTimesheet(TimeSheetModel ts) {
        try {
            return repo.addTimesheet(ts);
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public List<TimeSheetModel> getTimesheetHistory(int companyId) {
        try {
            return repo.getAllTimesheetsWithEmployeeInfo(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public double getTotalHours(List<TimeSheetModel> timeSheetList) {
        double totalHours = 0.0;
        for (TimeSheetModel timeSheet : timeSheetList) {
            totalHours += timeSheet.getHoursWorked();
        }
        return totalHours;
    }

    public boolean isTimesheetFilledToday(int empId, int companyId) {
        try {
            return repo.existsByDate(empId, companyId, LocalDate.now());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalHoursForDate(int empId, int companyId, LocalDate date) {
        try {
            return repo.getTotalHoursForDate(empId, companyId, date);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
