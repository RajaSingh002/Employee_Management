package view;

import utils.Constant;
import utils.ScannerSingleton;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = ScannerSingleton.getInstance();

    /*
     *********************************************************
     * @Method Name : showMainMenu
     * 
     * @description : Displays the main menu to the user.
     ********************************************************
     */
    public static void showMainMenu() {
        System.out.println(Constant.MAIN_MENU);
    }

    /*
     *********************************************************
     * @Method Name : getChoice
     * 
     * @description : Prompts the user to enter a numeric menu choice.
     * 
     * @return : int - validated menu choice input
     ********************************************************
     */
    public static int getChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(Constant.INVALID_NUMBER_INPUT);
            }
        }
    }

    /*
     *********************************************************
     * @Method Name : getCompanyNameOrBack
     * 
     * @description : Prompts the user to enter a company name or 'back' to return.
     * 
     * @return : String - user input (company name or 'back')
     ********************************************************
     */
    public static String getCompanyNameOrBack() {
        System.out.print(Constant.ENTER_COMPANY_NAME_OR_BACK + " ");
        return scanner.nextLine();
    }

    /*
     *********************************************************
     * @Method Name : getRetryChoice
     * 
     * @description : Asks the user if they want to try another company.
     * 
     * @return : String - trimmed, lowercased user input (e.g., "y" or "n")
     ********************************************************
     */
    public static String getRetryChoice() {
        System.out.print("Try another company? (y/n): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    /*
     *********************************************************
     * @Method Name : showCompanyNotFound
     * 
     * @description : Displays a message indicating the company was not found.
     ********************************************************
     */
    public static void showCompanyNotFound() {
        System.out.println(Constant.COMPANY_NOT_FOUND);
    }

    /*
     *********************************************************
     * @Method Name : showTryAnotherCompany
     * 
     * @description : Displays a message prompting user to try another company.
     ********************************************************
     */
    public static void showTryAnotherCompany() {
        System.out.println(Constant.TRY_ANOTHER_COMPANY);
    }

    /*
     *********************************************************
     * @Method Name : showLoginHeader
     * 
     * @description : Displays the login header with the company name.
     * 
     * @param : String companyName - company name to display
     ********************************************************
     */
    public static void showLoginHeader(String companyName) {
        System.out.println(Constant.LOGIN_HEADER_PREFIX + companyName);
    }

    /*
     *********************************************************
     * @Method Name : showLoginFailed
     * 
     * @description : Displays a login failure message.
     ********************************************************
     */
    public static void showLoginFailed() {
        System.out.println(Constant.LOGIN_FAILED);
    }

    /*
     *********************************************************
     * @Method Name : showExitMessage
     * 
     * @description : Displays a goodbye or exit message when the app terminates.
     ********************************************************
     */
    public static void showExitMessage() {
        System.out.println(Constant.EXIT_MESSAGE);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidMainMenuChoice
     * 
     * @description : Displays an error message for invalid main menu choice.
     ********************************************************
     */
    public static void showInvalidMainMenuChoice() {
        System.out.println(Constant.INVALID_MAIN_MENU_CHOICE);
    }

    /*
     *********************************************************
     * @Method Name : showLogoutMessage
     * 
     * @description : Displays a message confirming successful logout.
     ********************************************************
     */
    public static void showLogoutMessage() {
        System.out.println(Constant.LOGOUT_MESSAGE);
    }
}
