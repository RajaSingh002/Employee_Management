
package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import model.TimeSheetModel;
import repository.TimeSheetRepo;

public class TimeSheetService {

    /*
     *********************************************************
     * @Class Name : TimeSheetService
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Service layer for handling timesheet operations
     *********************************************************
     */

    private TimeSheetRepo repo;

    /*
     *********************************************************
     * @Method Name : TimeSheetService (Constructor)
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Initializes the TimeSheetService with a database connection
     * 
     * @param : Connection conn
     * 
     * @return : N/A
     *********************************************************
     */
    public TimeSheetService(Connection conn) {
        this.repo = new TimeSheetRepo(conn);
    }

    /*
     *********************************************************
     * @Method Name : addTimesheet
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Adds a new timesheet entry
     * 
     * @param : TimeSheetModel ts
     * 
     * @return : boolean indicating success or failure
     *********************************************************
     */
    public boolean addTimesheet(TimeSheetModel ts) {
        try {
            return repo.addTimesheet(ts);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     *********************************************************
     * @Method Name : getTimesheetHistory
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Retrieves all timesheets with employee info for a company
     * 
     * @param : int companyId
     * 
     * @return : List<TimeSheetModel> of timesheets or empty list on failure
     *********************************************************
     */
    public List<TimeSheetModel> getTimesheetHistory(int companyId) {
        try {
            return repo.getAllTimesheetsWithEmployeeInfo(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*
     *********************************************************
     * @Method Name : getTotalHours
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Calculates total hours worked from a list of timesheets
     * 
     * @param : List<TimeSheetModel> timeSheetList
     * 
     * @return : double total hours worked
     *********************************************************
     */
    public double getTotalHours(List<TimeSheetModel> timeSheetList) {
        double totalHours = 0.0;
        for (TimeSheetModel timeSheet : timeSheetList) {
            totalHours += timeSheet.getHoursWorked();
        }
        return totalHours;
    }

    /*
     *********************************************************
     * @Method Name : isTimesheetFilledToday
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Checks if an employee has filled a timesheet today
     * 
     * @param : int empId, int companyId
     * 
     * @return : boolean true if timesheet exists for today, false otherwise
     *********************************************************
     */
    public boolean isTimesheetFilledToday(int empId, int companyId) {
        try {
            return repo.existsByDate(empId, companyId, LocalDate.now());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     *********************************************************
     * @Method Name : getTotalHoursForDate
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Gets total hours worked by an employee for a specific date
     * 
     * @param : int empId, int companyId, LocalDate date
     * 
     * @return : double total hours worked, or 0.0 if error occurs
     *********************************************************
     */
    public double getTotalHoursForDate(int empId, int companyId, LocalDate date) {
        try {
            return repo.getTotalHoursForDate(empId, companyId, date);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
