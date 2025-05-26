package view;

import java.util.List;
import java.util.Scanner;
import utils.*;
import model.EmployeeModel;

public class HierarchyView {
    private static Scanner sc = ScannerSingleton.getInstance();

    /*
     *********************************************************
     * @Method Name : getEmployeeIdForHierarchy
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to enter an employee ID for viewing hierarchy
     * 
     * @param : none
     * 
     * @return : String - trimmed employee ID input by user
     ********************************************************
     */
    public static String getEmployeeIdForHierarchy() {
        System.out.print(Constant.ENTER_EMPLOYEE_ID);
        return sc.nextLine().trim();
    }

    /*
     *********************************************************
     * @Method Name : showInvalidEmployeeIdMessage
     * * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays a message indicating the employee ID is invalid
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidEmployeeIdMessage() {
        System.out.println(Constant.INVALID_EMPLOYEE_ID);
    }

    /*
     *********************************************************
     * @Method Name : showCEOHierarchyRestriction
     * * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays a message that CEO cannot view hierarchy
     * 
     * @return : void
     ********************************************************
     */
    public static void showCEOHierarchyRestriction() {
        System.out.println(Constant.CEO_VIEW_RESTRICTION);
    }

    /*
     *********************************************************
     * @Method Name : showNoHierarchyFound
     * * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays a message indicating no hierarchy was found for the
     * employee
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoHierarchyFound() {
        System.out.println(Constant.NO_HIERARCHY);
    }

    /*
     *********************************************************
     * @Method Name : displayHierarchy
     * 
     * * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays the hierarchy list of employees in a formatted table
     * 
     * @return : void
     ********************************************************
     */
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

    /*
     *********************************************************
     * @Method Name : showGenericError
     * * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays a generic error message with a prefix
     * 
     * @return : void
     ********************************************************
     */
    public static void showGenericError(String message) {
        System.out.println(Constant.ERROR_PREFIX + message);
    }
}
