package view;

import java.util.List;
import java.util.Scanner;
import utils.*;
import model.EmployeeModel;
import utils.ScannerSingleton;

public class HierarchyView {
    private static Scanner sc = ScannerSingleton.getInstance();

     public static String getEmployeeIdForHierarchy() {
        System.out.print(Constant.ENTER_EMPLOYEE_ID);
        return sc.nextLine().trim();
    }

    public static void showInvalidEmployeeIdMessage() {
        System.out.println(Constant.INVALID_EMPLOYEE_ID);
    }

    public static void showCEOHierarchyRestriction() {
        System.out.println(Constant.CEO_VIEW_RESTRICTION);
    }

    public static void showNoHierarchyFound() {
        System.out.println(Constant.NO_HIERARCHY);
    }

    public static void displayHierarchy(List<EmployeeModel> hierarchy) {
        System.out.println(Constant.TABLE_HEADER_TOP);
        System.out.println(Constant.TABLE_HEADER_LABELS);
        System.out.println(Constant.TABLE_HEADER_TOP);

        for (int i = 0; i < hierarchy.size(); i++) {
            EmployeeModel emp = hierarchy.get(i);
            String fullName = emp.getFirstName() + " " + emp.getLastName();
            System.out.printf("| %-20s | %-17s |\n", fullName, emp.getPosition());

            if (i < hierarchy.size() - 1) {
                System.out.println(Constant.TABLE_SPACER);
            }
        }

        System.out.println(Constant.TABLE_HEADER_TOP);
    }

    public static void showGenericError(String message) {
        System.out.println(Constant.ERROR_PREFIX + message);
    }
}
