package view;

import utils.Constant;

/*
 *********************************************************************************************************
 *  @Class Name      : SalaryView
 *  @Author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 12-05-2025
 *  @Description     : View class responsible for displaying salary-related prompts
 *                    and messages to the user in the Employee Management System.
 *********************************************************************************************************
 */

public class SalaryView {

    /*
     *********************************************************
     * @Method Name : SalarySlipEmployeeId
     * 
     * @description : Prompts user to enter employee ID for salary slip.
     * 
     * @return : void
     ********************************************************
     */
    public static void SalarySlipEmployeeId() {
        System.out.print(Constant.SALARY_SLIP_EMPLOYEE_ID_INPUT);
    }

    /*
     *********************************************************
     * @Method Name : showReturnToMenu
     * 
     * @description : Displays message to return to main menu.
     * 
     * @return : void
     ********************************************************
     */
    public static void showReturnToMenu() {
        System.out.println(Constant.RETURN_TO_MENU);
    }

    /*
     *********************************************************
     * @Method Name : showNegativeEmployeeIdError
     * 
     * @description : Displays error message for negative employee ID input.
     * 
     * @return : void
     ********************************************************
     */
    public static void showNegativeEmployeeIdError() {
        System.out.println(Constant.NEGATIVE_EMPLOYEE_ID_ERROR);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidNumericIdMessage
     * 
     * @description : Displays error message for invalid numeric employee ID input.
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidNumericIdMessage() {
        System.out.println(Constant.INVALID_NUMERIC_EMPLOYEE_ID);
    }

    /*
     *********************************************************
     * @Method Name : showSalarySlipMessage
     * 
     * @description : Displays the salary slip or related messages.
     * 
     * @param message : Message to display (salary slip or error).
     * 
     * @return : void
     ********************************************************
     */
    public static void showSalarySlipMessage(String message) {
        System.out.println(message);
    }
}
