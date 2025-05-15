package service;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.TimeSheetModel;
import repository.TimeSheetRepo;

public class TimeSheetService {

    private TimeSheetRepo repo;

    public TimeSheetService(Connection conn) {
        this.repo = new TimeSheetRepo(conn);
    }

    public boolean addTimesheet(TimeSheetModel ts) {
        return repo.addTimesheet(ts);
    }

    public List<TimeSheetModel> getTimesheetHistory(int companyId) {
        return repo.getAllTimesheetsWithEmployeeInfo(companyId);
    }

    public double getTotalHours(List<TimeSheetModel> timeSheetList) {
        double totalHours = 0.0;

        for (TimeSheetModel timeSheet : timeSheetList) {
            totalHours += timeSheet.getHoursWorked();
        }

        return totalHours;
    }

    public boolean isTimesheetFilledToday(int empId, int companyId) {
        return repo.existsByDate(empId, companyId, LocalDate.now());
    }

    public double getTotalHoursForDate(int empId, int companyId, LocalDate date) {
    return repo.getTotalHoursForDate(empId, companyId, date);
}


}
