package utils;

import java.util.List;

import model.EmployeeModel;
import model.TimeSheetModel;

public class Constant {

    public static void printEmployeeTable(List<EmployeeModel> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No Employees Found");
            return;
        }

        System.out.println(
                "+------------+----------------------+-----------------+----------------------------+-------------------+");
        System.out.println(
                "| EmpID      | Name                 | Position        | Email                      | Department        |");
        System.out.println(
                "+------------+----------------------+-----------------+----------------------------+-------------------+");

        for (EmployeeModel em : employees) {
            String fullName = em.getFirstName() + " " + em.getLastName();
            System.out.printf("| %-10s | %-20s | %-15s | %-26s | %-17s |\n",
                    "EID " + em.getId(),
                    fullName,
                    em.getPosition(),
                    em.getEmail(),
                    em.getDepartmentName());
        }

        System.out.println(
                "+------------+----------------------+-----------------+----------------------------+-------------------+");
    }

    public static final List<String> SKILLS = List.of(
            "Java", "Python", "C++", "JavaScript", "SQL",
            "HTML", "CSS", "Spring", "Sales Strategy", "React",
            "Recruitment", "Node.js", "Lead Generation", "Kotlin", "Content Creation",
            "Git", "Docker", "Kubernetes", "AWS", "Payroll Management");

    public static void printSkillTable(List<String> skills) {
        final int columns = 4;
        final int colWidth = 20;

        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < skills.size(); i++) {
            System.out.printf("%-" + colWidth + "s", skills.get(i));
            if ((i + 1) % columns == 0 || i == skills.size() - 1) {
                System.out.println();
            }
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public static void showMainMenu() {
        System.out.println("\n!! Welcome to Employee Management System !!");
        System.out.println("1. Sign Up (Register New Company)");
        System.out.println("2. Login (Already Registered)");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public static final String LOGOUT_SUCCESS = "Logged Out Successfully";
    public static final String INVALID_CHOICE = "Invalid choice! Please select a valid option.";
    public static final String INVALID_NUMBER_INPUT = "Invalid input. Please enter a number.";
    public static final String EXIT_MESSAGE = "Exiting the system. Goodbye!";
    public static final String INVALID_MAIN_MENU_CHOICE = "Invalid choice. Please enter 1, 2 or 3.";
    public static final String ENTER_COMPANY_NAME_OR_BACK = "Enter your company name or Enter Back : ";
    public static final String COMPANY_NOT_FOUND = "Company not found.";
    public static final String TRY_ANOTHER_COMPANY = "Try another company name? (y/n): ";
    public static final String LOGOUT_MESSAGE = "User logged out. Please login again.\n";
    public static final String LOGIN_FAILED = "Login failed. Please try again.\n";
    public static final String LOGIN_HEADER_PREFIX = "Login to ";

    public static final String TIMESHEET_ALREADY_FILLED = "You have already filled your timesheet for today.";
    public static final String TASK_DESCRIPTION_INPUT = "Enter task description (or 'back' to return): ";
    public static final String TASK_EMPTY_ERROR = "Task description cannot be empty.";
    public static final String HOURS_WORKED_INPUT = "Enter hours worked (max 8) or 'back' to return: ";
    public static final String INVALID_HOURS_RANGE = "Please enter a number between 0 and 8.";
    public static final String INVALID_NUMERIC_INPUT = "Invalid input. Please enter a numeric value.";
    public static final String TIMESHEET_ADDED_SUCCESS = "Timesheet added successfully.";
    public static final String TIMESHEET_ADD_FAILURE = "Failed to add timesheet.";

    public static final String USER_NAME_ALREADY_EXISTS = "Username is Already Exists";
    public static final String EMAIL_ALREADY_EXISTS = "This email is Already Exists. Use a different Email.";

    public static final String ACCESS_DENIED_FOR_TIMESHEETS = "Access denied. Only the CEO can view all timesheets.";
    public static final String NO_TIMESHEET_DATA_FOUND = "No timesheet data found.";

    public static final String CURRENT_SKILLS_HEADER = "\nYour Current Skills:";
    public static final String NO_SKILLS_MESSAGE = "None";
    public static final String ALLOWED_SKILLS_HEADER = "\nAllowed Skills:";
    public static final String SKILL_INPUT_INSTRUCTION = "\nEnter skills to add (comma-separated), or type 'back' to cancel: ";
    public static final String INVALID_SKILL_INPUT = "No valid skills provided.";
    public static final String SKILL_ADDITION_SUCCESS = "Skills added successfully.";

    public static final String SALARY_SLIP_EMPLOYEE_ID_INPUT = "Enter Employee ID to generate salary slip (or type 'back' to return): ";
    public static final String RETURN_TO_MENU = "Returning to previous menu...";
    public static final String NEGATIVE_EMPLOYEE_ID_ERROR = "Employee ID cannot be negative. Please try again.";
    public static final String INVALID_NUMERIC_EMPLOYEE_ID = "Invalid input! Please enter a numeric Employee ID or type 'back'.";

    public static final String ENTER_COMPANY_NAME = "Enter your company name to register Or (Back): ";
    public static final String INVALID_COMPANY_NAME = "Invalid company name. Only letters, numbers and spaces allowed (3-50 characters).";

    public static final String ENTER_FIRST_NAME = "Enter Your First Name  Or (Back):";
    public static final String INVALID_FIRST_NAME = "Invalid last name. Only letters allowed (2-30 characters).";

    public static final String ENTER_LAST_NAME = "Enter Your Last Name  Or (Back):";
    public static final String INVALID_LAST_NAME = "Invalid last name. Only letters allowed (2-30 characters).";

    public static final String ENTER_PERSONAL_EMAIL = "Enter Your Email  Or (Back): ";
    public static final String INVALID_EMAIL = "Invalid email format";

    public static final String ENTER_USERNAME = "Enter admin username:  ";
    public static final String INVALID_USERNAME = "Invalid username. Use 4-20 characters (letters, numbers, underscores).";

    public static final String ENTER_PASSWORD = "Enter password:  ";
    public static final String INVALID_PASSWORD = "Invalid password. Must be 6-20 characters with at least one uppercase, one lowercase, one digit, and one special character.";

    public static final String ENTER_COMPANY_EMAIL = "Enter company email:  Or (Back): ";

    public static final String COMPANY_ALREADY_REGISTERED = "Company already registered. Redirecting to login...";
    public static final String REGISTRATION_SUCCESS = "Company registered successfully. Please login now.";

    public static final String LOGIN_FAILURE_MESSAGE = "Invalid username or password.";
    public static final String RETRY_MESSAGE = "Do you want to try again? (Yes/No): ";

    public static final String LOGIN_SCREEN_HEADER = "\n=== Welcome to %s's Portal ===";
    public static final String LOGIN_SCREEN_SUBHEADER = "Please log in:\n";

    public static final String LOGIN_SUCCESS_PREFIX = "Login successful! Welcome, ";
    public static final String LOGIN_ROLE_PREFIX = ". Role: ";

    public static final String ENTER_START_DATE = "Enter leave start date (YYYY-MM-DD)";
    public static final String ENTER_END_DATE = "Enter leave end date (YYYY-MM-DD)";
    public static final String BACK_OPTION = " or type 'back' to cancel: ";
    public static final String INVALID_DATE_FORMAT = "Invalid date format. Please enter the date in YYYY-MM-DD format.";
    public static final String START_DATE_BEFORE_TODAY = "Start date cannot be before today's date. Please enter a valid date.";
    public static final String END_DATE_BEFORE_START = "End date cannot be before start date. Please enter a valid end date.";
    public static final String NO_LEAVE_HISTORY = "No leave history found.";
    public static final String LEAVE_STATUS_PREFIX = "Leave Status: ";
    public static final String LEAVE_HISTORY_SEPARATOR = "--------------------------";
    public static final String LEAVE_HEADER = "%-15s %-10s %-20s %-20s %-10s%n";
    public static final String LEAVE_HEADER_LINE = "------------------------------------------------------------------------------";
    public static final String LEAVE_ROW = "%-15d %-10d %-20s %-20s %-10s%n";
    public static final String APPROVE_QUESTION = "Approve leave ID %d? (yes/no): ";

    public static final String ENTER_EMPLOYEE_ID = "Enter Employee ID to view hierarchy (or 'back' to cancel): ";
    public static final String INVALID_EMPLOYEE_ID = "Invalid input. Please enter a numeric Employee ID.";
    public static final String CEO_VIEW_RESTRICTION = "CEO cannot view their own hierarchy.";
    public static final String NO_HIERARCHY = "No hierarchy found.";
    public static final String ERROR_PREFIX = "An error occurred: ";

    public static final String TABLE_HEADER_TOP = "+----------------------+-------------------+";
    public static final String TABLE_HEADER_LABELS = "| Name                 | Position          |";
    public static final String TABLE_SPACER = "|                     |                   |";

    public static final String LINE = "+----------+------------+---------------------+---------------------+-----------+";

    public static void View() {
        System.out.println("+----------+------------+---------------------+---------------------+-----------+");
        System.out.println("| Emp ID   | Date       | In Time             | Out Time            | Status    |");
        System.out.println("+----------+------------+---------------------+---------------------+-----------+");

    }

    public static void View1() {
        System.out.println("+---------------------+---------------------+-----------+");
        System.out.println("| In Time             | Out Time            | Status    |");
        System.out.println("+---------------------+---------------------+-----------+");
    }

    public static final String NO_ATTENDANCE = "No attendance records found for the company.";

    public static final String TODAY_ATTENDANCE = "No attendance recorded for today.";

    public static final String[] CEO_MENU = {
            "View Hierarchy",
            "View All Employees",
            "Time-In",
            "Time-Out",
            "View All Timesheets",
            "Approve Leave",
            "Logout",
            "View Working Log Reports",
            "View Your Today's Attendance",
            "Hire Employee",
            "Exit"
    };

    public static final String[] HR_MENU = {
            "Hire Employee",
            "Update Employee",
            "Fire Employee",
            "Time-In",
            "Time-Out",
            "View Your Today's Attendance",
            "View Attendance History",
            "Print Salary",
            "Add Skills",
            "View All Employees",
            "Logout",
            "Exit"
    };

    public static final String[] MANAGER_MENU = {
            "Approve Leaves",
            "Apply Leaves",
            "Add Timesheet",
            "Time In",
            "Time Out",
            "Add Skills",
            "View Your Today's Attendance",
            "Logout",
            "Exit"
    };

    public static final String[] TECH_LEAD_MENU = {
            "Apply Leaves",
            "Add Timesheet",
            "Time In",
            "Time Out",
            "Add Skills",
            "View Your Today's Attendance",
            "Logout",
            "Exit"
    };

    public static final String[] DEVELOPER_MENU = {
            "Time In",
            "Time Out",
            "Add Timesheet",
            "Apply For Leave",
            "Add Skills",
            "View Your Today's Attendance",
            "Logout",
            "Exit"
    };

    public static final String URL = "jdbc:mysql://localhost:3306/employee";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Raja@123";

    public static final String ENTER_CHOICE = "Enter your choice: ";
    public static final String INVALIDN_CHOICE = "Invalid input. Please enter a valid number given above";

    public static final String EMPLOYEE_ID_TO_UPDATE = "Enter Employee ID to update (or 'back' to cancel): ";
    public static final String INVALID_ID = "Invalid ID.";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found.";

    public static final String INVALID_MANAGER_ID = "Invalid manager ID. Available managers:";
    public static final String NON_NUMERIC_MANAGER_ID = "Please enter a valid numeric Manager ID.";

    public static final String EMPLOYEE_UPDATED_SUCCESS = "Employee updated successfully.";

    public static final String FIRST_NAME = "Enter First Name (or 'back'): ";

    public static final String LAST_NAME_INPUT = "Enter Last Name (or 'back'): ";
    public static final String EMAIL_INPUT = "Enter Email (or 'back'): ";
    public static final String INVALID_NAME = "Invalid input. Name must contain only letters.";

    public static final String SALARY_INPUT = "Enter base salary (or type 'back' to cancel): ";
    public static final String INVALID_SALARY = "Invalid input. Please enter a valid salary amount.";
    public static final String NEGATIVE_SALARY = "Salary cannot be negative.";

    public static final String DELETE_ID_INPUT = "Enter Employee ID to delete (or 'back' to cancel): ";
    public static final String DELETE_SUCCESS = "Employee deleted successfully.";
    public static final String DELETE_FAILURE = "Employee not found or already deleted.";
    public static final String INVALID_NUMERIC_ID = "Invalid input. Please enter a numeric Employee ID.";

    public static final String POSITION_SELECTION_HEADER = "Select Position (or type 'back' to cancel):";
    public static final String OTHER_OPTION = ". Other";
    public static final String CUSTOM_POSITION_INPUT = "Enter position: ";
    public static final String POSITION_EMPTY = "Position cannot be empty.";
    public static final String INVALID_OPTION = "Invalid choice. Please select a valid option.";
    public static final String INVALID_NUMBER = "Invalid input. Please enter a number.";
    public static final String MANAGER_ID_INPUT = "Enter Manager ID: ";
    public static final String INVALID_MANAGER_ID1 = "Invalid ID. Must be numeric.";

    public static final String DEPARTMENT_NAME_INPUT = "Enter Department Name: ";

    public static final String USERNAME_INPUT = "Enter Username: ";
    public static final String PASSWORD_INPUT = "Enter Password: ";

    public static final String BACK = "back";

    public static void ManagerTable() {
        System.out.println("+-------------------+--------------------+");
        System.out.println("| Manager ID        | Manager Name       |");
        System.out.println("+-------------------+--------------------+");
    }

    public static void showTimesheet(TimeSheetModel ts) {

        System.out.printf("%-12d %-15s %-40s %-8.2f%n",
                ts.getEmpId(), ts.getDate(), ts.getDescription(), ts.getHoursWorked());
        System.out.println("----------------------------------------------------------------------------");
    }

    public static final String TIMESHEET_HEADER = "\n--- All Timesheets ---";
   public static final String TIMESHEET_HEADERs= "%-12s %-15s %-40s %-8s%n";

    public static final String EMPLOYEE_ID = "Enter Employee ID (or type 'back' to return): ";
    public static final String START_DATE = "Enter Start Date (YYYY-MM-DD) (or type 'back'): ";
    public static final String END_DATE = "Enter End Date (YYYY-MM-DD) (or type 'back'): ";

    public static final String CEMPLOYEE_NOT_FOUND = "Employee not found or does not belong to this company.";

    public static final String END_BEFORE_START = "End date cannot be before start date.";

    public static final String NO_ATTENDANCE_IN_RANGE = "No attendance records found in the given date range.";

    public static final String EMAIL_USED_BY_FIRED = "This email is already used by a fired employee.";
    public static final String EMAIL_USED_BY_ACTIVE = "This email is already associated with an active employee.";
    public static final String NO_MANAGERS_AVAILABLE = "No managers available in the company. Cannot proceed.";
    public static final String INVALID_MANAGER = "Invalid manager. Must be a valid manager in your company.";
    public static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee added successfully.";
    public static final String CONFIRM_MANAGER_SELECTION = "Is this the correct manager?";

    public static final String LEAVE_APPLY_CANCELLED = "Leave application cancelled.";
    public static final String LEAVE_OVERLAP = "Cannot apply for leave: Overlapping with an existing leave.";
    public static final String LEAVE_APPLIED_SUCCESSFULLY = "Leave applied successfully.";
    public static final String LEAVE_APPLICATION_FAILED = "Failed to apply leave. Not enough leave balance.";
    public static final String NO_LEAVE_REQUESTS = "No leave requests to approve.";
    public static final String INVALID_APPROVAL_INPUT = "Invalid input! Please enter 'yes' to approve or 'no' to reject.";
    public static final String LEAVE_APPROVED_SUCCESS = "Leave approved. Leave balance updated.";
    public static final String LEAVE_REJECTED_SUCCESS = "Leave rejected.";
    public static final String LEAVE_STATUS_UPDATE_FAILED = "Failed to update leave status.";

    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS employee";
    public static final String USE_DATABASE = "USE employee";

    public static final String CREATE_COMPANIES_TABLE = """
                    CREATE TABLE IF NOT EXISTS companies (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        company_name VARCHAR(100) NOT NULL UNIQUE,
                        username VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        company_email VARCHAR(100) NOT NULL UNIQUE
                    );
            """;

    public static final String CREATE_DEPARTMENTS_TABLE = """
                    CREATE TABLE IF NOT EXISTS departments (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        department_name VARCHAR(100) NOT NULL,
                        company_id INT NOT NULL,
                        description TEXT,
                        is_active BOOLEAN DEFAULT true,
                        UNIQUE (department_name, company_id),
                        FOREIGN KEY (company_id) REFERENCES companies(id)
                    );
            """;

    public static final String CREATE_EMPLOYEES_TABLE = """
                    CREATE TABLE IF NOT EXISTS employees (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        position VARCHAR(50) NOT NULL,
                        manager_id INT,
                        skills TEXT NOT NULL,
                        department_id INT,
                        joining_date DATE NOT NULL,
                        is_active BOOLEAN DEFAULT true,
                        company_id INT NOT NULL,
                        FOREIGN KEY (manager_id) REFERENCES employees(id),
                        FOREIGN KEY (department_id) REFERENCES departments(id),
                        FOREIGN KEY (company_id) REFERENCES companies(id)
                    );
            """;

    public static final String CREATE_USER_TABLE = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        role ENUM('CEO', 'MANAGER', 'TECHLEAD', 'DEVELOPER') NOT NULL,
                        is_active BOOLEAN DEFAULT true,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    );
            """;

    public static final String CREATE_ATTENDANCE_TABLE = """
                    CREATE TABLE IF NOT EXISTS attendance (
                        attendance_id INT PRIMARY KEY AUTO_INCREMENT,
                        emp_id INT NOT NULL,
                        date DATE NOT NULL,
                        in_time DATETIME NOT NULL,
                        out_time DATETIME,
                        status VARCHAR(20) DEFAULT 'PRESENT',
                        FOREIGN KEY (emp_id) REFERENCES employees(id)
                    );
            """;

    public static final String CREATE_LEAVE_REQUESTS_TABLE = """
                    CREATE TABLE IF NOT EXISTS leave_requests (
                        leave_id INT PRIMARY KEY AUTO_INCREMENT,
                        emp_id INT NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        type VARCHAR(50) NOT NULL,
                        status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                        approved_by INT,
                        leave_balance INT NOT NULL,
                        company_id INT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (emp_id) REFERENCES employees(id),
                        FOREIGN KEY (approved_by) REFERENCES employees(id),
                        FOREIGN KEY (company_id) REFERENCES companies(id)
                    );
            """;

    public static final String CREATE_TIMESHEET_TABLE = """
                    CREATE TABLE IF NOT EXISTS timesheet (
                        timesheet_id INT PRIMARY KEY AUTO_INCREMENT,
                        emp_id INT NOT NULL,
                        company_id INT NOT NULL,
                        date DATE NOT NULL,
                        task_description VARCHAR(255) NOT NULL,
                        hours_worked DECIMAL(4, 2) NOT NULL CHECK (hours_worked <= 8),
                        FOREIGN KEY (emp_id) REFERENCES employees(id),
                        FOREIGN KEY (company_id) REFERENCES companies(id),
                        UNIQUE (emp_id, company_id, date, task_description)
                    );
            """;

    public static final String INSERT_IN_TIME_SQL = """
                INSERT INTO attendance (emp_id, company_id, date, in_time, status)
                VALUES (?, ?, ?, ?, ?)
            """;

    public static final String SELECT_TODAY_ATTENDANCE_SQL = """
                SELECT * FROM attendance WHERE emp_id = ? AND company_id = ? AND date = ?
            """;

    public static final String SELECT_OUT_TIME_SQL = """
                SELECT out_time FROM attendance WHERE emp_id = ? AND company_id = ? AND date = ?
            """;

    public static final String UPDATE_OUT_TIME_SQL = """
                UPDATE attendance SET out_time = ?, status = ?
                WHERE emp_id = ? AND company_id = ? AND date = ?
            """;

    public static final String SELECT_ATTENDANCE_HISTORY_SQL = """
                SELECT * FROM attendance WHERE company_id = ? AND date >= ? ORDER BY date DESC, emp_id
            """;

    public static final String SELECT_PRESENT_DAYS_CURRENT_MONTH_SQL = """
                SELECT COUNT(*) FROM attendance WHERE emp_id = ? AND company_id = ? AND status = 'PRESENT'
                AND MONTH(date) = MONTH(CURRENT_DATE) AND YEAR(date) = YEAR(CURRENT_DATE)
            """;

    public static final String SELECT_ATTENDANCE_BETWEEN_DATES_SQL = """
                SELECT emp_id, date, in_time, out_time
                FROM attendance
                WHERE emp_id = ? AND date BETWEEN ? AND ?
                ORDER BY date;
            """;

    public static final String CHECK_COMPANY_EXISTS = "SELECT 1 FROM companies WHERE company_name = ?";
    public static final String CHECK_USERNAME_EXISTS = "SELECT 1 FROM employees WHERE username = ?";
    public static final String CHECK_EMAIL_EXISTS = "SELECT 1 FROM companies WHERE company_email = ?";
    public static final String INSERT_COMPANY_SQL = "INSERT INTO companies (company_name, company_email) VALUES (?, ?)";
    public static final String INSERT_CEO_SQL = """
                INSERT INTO employees (
                    first_name, last_name, email, position,
                    manager_id, joining_date, is_active, skills,
                    company_id, department_name, username, password
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String INSERT_LEAVE_REQUEST = "INSERT INTO leave_requests (emp_id, start_date, end_date, status) VALUES (?, ?, ?, 'PENDING')";
    public static final String SELECT_LEAVES_BY_EMPLOYEE = "SELECT * FROM leave_requests WHERE emp_id = ?";
    public static final String SELECT_PENDING_LEAVES_TO_APPROVE = """
            SELECT * FROM leave_requests l
            JOIN employees e ON l.emp_id = e.id
            WHERE l.status = 'PENDING' AND
            (
                (? = 'MANAGER' AND e.manager_id = ?) OR
                (? = 'CEO' AND e.position = 'MANAGER')
            )
            """;
    public static final String UPDATE_LEAVE_STATUS = "UPDATE leave_requests SET status = ?, approved_by = ? WHERE leave_id = ?";
    public static final String SELECT_EMPLOYEE_BALANCE = "SELECT leaveBalance FROM employees WHERE id = ?";
    public static final String UPDATE_EMPLOYEE_BALANCE = "UPDATE employees SET leaveBalance = leaveBalance - ? WHERE id = ?";
    public static final String SELECT_LEAVE_BY_ID = "SELECT * FROM leave_requests WHERE leave_id = ?";
    public static final String COUNT_APPROVED_LEAVES_IN_MONTH = """
            SELECT start_date, end_date FROM leave_requests
            WHERE emp_id = ? AND status = 'APPROVED'
            AND MONTH(start_date) = ? AND YEAR(start_date) = ?
            """;

    public static final String INSERT_TIMESHEET = "INSERT INTO timesheet (emp_id, company_id, date, task_description, hours_worked) VALUES (?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_TIMESHEETS_WITH_EMPLOYEE_INFO = """
                SELECT t.timesheet_id, t.emp_id, t.date, t.hours_worked, t.task_description,
                       e.first_name, e.last_name, e.position
                FROM timesheet t
                JOIN employees e ON t.emp_id = e.id
                WHERE t.company_id = ?
                ORDER BY t.date DESC, e.id
            """;

    public static final String CHECK_TIMESHEET_EXISTS_BY_DATE = "SELECT 1 FROM timesheet WHERE emp_id = ? AND company_id = ? AND date = ?";

    public static final String GET_TOTAL_HOURS_FOR_DATE = "SELECT SUM(hours_worked) AS total_hours FROM timesheet WHERE emp_id = ? AND company_id = ? AND date = ?";

    public static final String HOURS_WORKED_WITH_REMAINING = "Enter hours worked (Remaining: %s hours): ";
    public static final String INVALID_HOURS_FOR_REMAINING = "Invalid input. Please enter a value between 0 and %s.";
    public static final String TIMESHEET_FULLY_LOGGED = "You have successfully logged a total of 8 hours for today. Timesheet is complete.";

     public static final String CLOCKED_IN="You have already clocked in today";

      public static final String CLOCKED_IN_SUCCESS="Thank You !! Clocked Out";
     public static final String CHECK_CLOCKED= "You need to clock in before clocking out.";
         public static final String CLOCKED_OUT="You have already clocked out today.";
       public static final String UPDATE_MSG=   "Employee updated successfully.";
       public static final String UPDATE_FAILED_MSG="Update failed. No matching employee found.";
}
