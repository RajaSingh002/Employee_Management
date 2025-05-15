package view;

import java.util.List;
import java.util.Scanner;

import javax.xml.validation.Validator;

import model.EmployeeModel;
import utils.Constant;
import utils.Role;

import utils.ScannerSingleton;

public class EmployeeView {

    private static Scanner sc = ScannerSingleton.getInstance();

    public static String FirstName() {
        while (true) {
            System.out.print(Constant.FIRST_NAME);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase(Constant.BACK))
                return null;
            if (input.matches("[a-zA-Z]+"))
                return input;
            System.out.println(Constant.INVALID_NAME);
        }
    }

    public static String LastName() {
        while (true) {
            System.out.print(Constant.LAST_NAME_INPUT);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase(Constant.BACK))
                return null;
            if (input.matches("[a-zA-Z]+"))
                return input;
            System.out.println(Constant.INVALID_NAME);
        }
    }

    public static String Email() {
        while (true) {
            System.out.print(Constant.EMAIL_INPUT);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase(Constant.BACK))
                return null;
            if (input.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))
                return input;
            System.out.println(Constant.INVALID_EMAIL);
        }
    }

    public static double Salary() {
        while (true) {
            System.out.print(Constant.SALARY_INPUT);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase(Constant.BACK))
                return -1;
            try {
                double salary = Double.parseDouble(input);
                if (salary < 0) {
                    System.out.println(Constant.NEGATIVE_SALARY);
                } else {
                    return salary;
                }
            } catch (NumberFormatException e) {
                System.out.println(Constant.INVALID_SALARY);
            }
        }
    }

    public static String getEmployeeIdToDelete() {
        System.out.print(Constant.DELETE_ID_INPUT);
        return sc.nextLine().trim();
    }

    public static void showEmployeeDeletedSuccess() {
        System.out.println(Constant.DELETE_SUCCESS);
    }

    public static void showEmployeeDeleteFailure() {
        System.out.println(Constant.DELETE_FAILURE);
    }

    public static void showInvalidEmployeeIdMessage() {
        System.out.println(Constant.INVALID_NUMERIC_ID);
    }

    public static String Position() {
        List<Role> validRoles = List.of(Role.MANAGER, Role.TECHLEAD, Role.DEVELOPER);

        while (true) {
            System.out.println(Constant.POSITION_SELECTION_HEADER);
            for (int i = 0; i < validRoles.size(); i++) {
                System.out.println((i + 1) + ". " + validRoles.get(i).name());
            }
            System.out.println((validRoles.size() + 1) + Constant.OTHER_OPTION);

            System.out.print("Enter your choice (1-" + (validRoles.size() + 1) + "): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("back"))
                return null;

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= validRoles.size()) {
                    return validRoles.get(choice - 1).name();
                } else if (choice == validRoles.size() + 1) {
                    System.out.print(Constant.CUSTOM_POSITION_INPUT);
                    String customPosition = sc.nextLine().trim();
                    if (customPosition.equalsIgnoreCase("back"))
                        return null;
                    if (!customPosition.isEmpty())
                        return customPosition;
                    else
                        System.out.println(Constant.POSITION_EMPTY);
                } else {
                    System.out.println(Constant.INVALID_OPTION);
                }
            } catch (NumberFormatException e) {
                System.out.println(Constant.INVALID_NUMBER);
            }
        }
    }

    public static void displayManagers(List<EmployeeModel> managers) {
        Constant.ManagerTable();
        for (EmployeeModel manager : managers) {
            System.out.printf("| %-17d | %-18s |\n",
                    manager.getId(),
                    manager.getFirstName() + " " + manager.getLastName());
        }
        System.out.println(Constant.LINE);
    }

    public static int ManagerId() {
        while (true) {
            System.out.print(Constant.MANAGER_ID_INPUT);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Constant.INVALID_MANAGER_ID1);
            }
        }
    }

    public static String Department() {
        System.out.print(Constant.DEPARTMENT_NAME_INPUT);
        return sc.nextLine().trim();
    }

    public static String Username() {
        System.out.print(Constant.USERNAME_INPUT);
        return sc.nextLine().trim();
    }

    public static String Password() {
        System.out.print(Constant.PASSWORD_INPUT);
        return sc.nextLine().trim();
    }

    public static void showMessage(String msg) {
        System.out.println(msg);
    }

    public static boolean confirm(String msg) {
        Scanner sc = ScannerSingleton.getInstance();
        System.out.print(msg + " (yes/no): ");
        String input = sc.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    private static void printMenu(String title, String[] options) {
        int width = 50;
        String border = "═".repeat(width - 2);

        System.out.println();
        System.out.printf("╔%s╗\n", border);
        System.out.printf("║ %-46s ║\n", "  " + title);
        System.out.printf("╠%s╣\n", border);

        for (int i = 0; i < options.length; i++) {
            System.out.printf("║ %-2d. %-41s ║\n", i + 1, options[i]);
        }

        System.out.printf("╚%s╝\n\n", border);
    }

    public static void showCEOMenu() {

        printMenu("CEO DashBoard", Constant.CEO_MENU);
    }

    public static void showHrMenu() {
        printMenu("HR DashBoard", Constant.HR_MENU);
    }

    public static void showManagerMenu() {
        printMenu("Manager DashBoard", Constant.MANAGER_MENU);
    }

    public static void showTechLeadMenu() {
        printMenu("Tech Lead DashBoard", Constant.TECH_LEAD_MENU);
    }

    public static void showDeveloperMenu() {
        printMenu("Employee DashBoard", Constant.DEVELOPER_MENU);
    }

    public static int getChoice() {
        while (true) {
            System.out.print(Constant.ENTER_CHOICE);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {

                System.out.println(Constant.INVALIDN_CHOICE);
            }
        }
    }

    public static void EmployeeIdToUpdate() {
        System.out.print(Constant.EMPLOYEE_ID_TO_UPDATE);
    }

    public static void showInvalidIdMessage() {
        System.out.println(Constant.INVALID_ID);
    }

    public static void showEmployeeNotFound() {
        System.out.println(Constant.EMPLOYEE_NOT_FOUND);
    }

    public static void ManagerId(int currentManagerId) {
        System.out.print("Manager ID [" + currentManagerId + "]: (or 'back' to cancel): ");
    }

    public static void showInvalidManagerId() {
        System.out.println(Constant.INVALID_MANAGER_ID);
    }

    public static void showAvailableManagers(List<EmployeeModel> managers) {
        for (EmployeeModel m : managers) {
            System.out.println("ID: " + m.getId() + ", Name: " + m.getFirstName() + " " + m.getLastName());
        }
    }

    public static void showNonNumericManagerId() {
        System.out.println(Constant.NON_NUMERIC_MANAGER_ID);
    }

    public static void DepartmentName(String currentDepartment) {
        System.out.print("Department Name [" + currentDepartment + "]: (or 'back' to cancel): ");
    }

    public static void Skills(List<String> currentSkills) {
        System.out.print("Skills [" + currentSkills + "]:  (or 'back' to cancel): ");
    }

    public static void showEmployeeUpdateSuccess() {
        System.out.println(Constant.EMPLOYEE_UPDATED_SUCCESS);
    }

    public static String askEmployeeIdToUpdate() {
        System.out.print("Enter Employee ID to update (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askFirstName(String current) {
        System.out.print("First Name [" + current + "] (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askLastName(String current) {
        System.out.print("Last Name [" + current + "] (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askEmail(String current) {
        System.out.print("Email [" + current + "] (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askPosition() {
        System.out.print("Enter new Position (or type 'back'): ");
        String input = sc.nextLine().trim();
        return input.equalsIgnoreCase(Constant.BACK) ? null : input;
    }

    public static String askManagerId(int currentManagerId) {
        System.out.print("Manager ID [" + currentManagerId + "] (or press Enter to skip, 'back' to cancel): ");
        return sc.nextLine().trim();
    }

    public static String askDepartmentName(String current) {
        System.out.print("Department [" + current + "] (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askSkills(List<String> currentSkills) {
        System.out.println("Current Skills: " + currentSkills);
        System.out.print("Add Skills (comma separated) or type 'back': ");
        return sc.nextLine().trim();
    }

    public static String askUsername(String current) {
        System.out.print("Username [" + current + "] (or type 'back'): ");
        return sc.nextLine().trim();
    }

    public static String askPassword() {
        System.out.print("Password [hidden] (or type 'back'): ");
        return sc.nextLine().trim();
    }

  
    public static void showInvalidFirstName() {
        System.out.println(Constant.INVALID_NAME);
    }

    public static void showInvalidLastName() {
        System.out.println(Constant.INVALID_NAME);
    }

    public static void showInvalidEmail() {
        System.out.println(Constant.INVALID_EMAIL);
    }

    public static void showDuplicateFieldMessage(String message) {
     System.out.println("Update failed: " + message);
}


}
