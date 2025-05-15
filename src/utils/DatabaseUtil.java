package utils;

import java.sql.*;

public class DatabaseUtil {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(Constant.URL, Constant.USERNAME, Constant.PASSWORD);
        } catch (SQLException e) {
            return null;
        }
    }
}