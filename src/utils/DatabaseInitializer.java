package utils;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try {
            createDatabase();
            createTables();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void createDatabase() {
        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(Constant.CREATE_DATABASE);
            stmt.executeUpdate(Constant.USE_DATABASE);
        } catch (Exception e) {
          e.getMessage();
        }
    }

    private static void createTables() {
        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(Constant.CREATE_COMPANIES_TABLE);
            stmt.executeUpdate(Constant.CREATE_DEPARTMENTS_TABLE);
            stmt.executeUpdate(Constant.CREATE_EMPLOYEES_TABLE);
            stmt.executeUpdate(Constant.CREATE_USER_TABLE);
            stmt.executeUpdate(Constant.CREATE_ATTENDANCE_TABLE);
            stmt.executeUpdate(Constant.CREATE_LEAVE_REQUESTS_TABLE);
            stmt.executeUpdate(Constant.CREATE_TIMESHEET_TABLE);
        } catch (Exception e) {
           e.getMessage();
        }
    }

}
