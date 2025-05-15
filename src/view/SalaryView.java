package view;

import utils.Constant;

public class SalaryView {
     public static void SalarySlipEmployeeId() {
        System.out.print(Constant.SALARY_SLIP_EMPLOYEE_ID_INPUT);
    }

    public static void showReturnToMenu() {
        System.out.println(Constant.RETURN_TO_MENU);
    }

    public static void showNegativeEmployeeIdError() {
        System.out.println(Constant.NEGATIVE_EMPLOYEE_ID_ERROR);
    }

    public static void showInvalidNumericIdMessage() {
        System.out.println(Constant.INVALID_NUMERIC_EMPLOYEE_ID);
    }

}
