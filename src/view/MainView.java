package view;

import utils.Constant;
import utils.ScannerSingleton;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = ScannerSingleton.getInstance();

    public static void showMainMenu() {
        System.out.println(Constant.MAIN_MENU);
    }

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

    public static String getCompanyNameOrBack() {
        System.out.print(Constant.ENTER_COMPANY_NAME_OR_BACK + " ");
        return scanner.nextLine();
    }

    public static String getRetryChoice() {
        System.out.print("Try another company? (y/n): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public static void showCompanyNotFound() {
        System.out.println(Constant.COMPANY_NOT_FOUND);
    }

    public static void showTryAnotherCompany() {
        System.out.println(Constant.TRY_ANOTHER_COMPANY);
    }

    public static void showLoginHeader(String companyName) {
        System.out.println(Constant.LOGIN_HEADER_PREFIX + companyName);
    }

    public static void showLoginFailed() {
        System.out.println(Constant.LOGIN_FAILED);
    }

    public static void showExitMessage() {
        System.out.println(Constant.EXIT_MESSAGE);
    }

    public static void showInvalidMainMenuChoice() {
        System.out.println(Constant.INVALID_MAIN_MENU_CHOICE);
    }

    public static void showLogoutMessage() {
        System.out.println(Constant.LOGOUT_MESSAGE);
    }
}
