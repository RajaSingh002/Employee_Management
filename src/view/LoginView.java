package view;

import java.util.Scanner;

import utils.*;
public class LoginView {
    private Scanner scanner = new Scanner(System.in);
    private String companyName;

   

    public LoginView() {
    }

    public LoginView(String companyName) {
        this.companyName = companyName;
    }

    public String getUsername() {
        System.out.print(Constant.ENTER_USERNAME);
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.print(Constant.ENTER_PASSWORD);
        return scanner.nextLine();
    }

    public void showLoginScreen() {
        ClearScreen.getInstance().clearScreen();
       System.out.println(String.format(Constant.LOGIN_SCREEN_HEADER, companyName));
        System.out.println(Constant.LOGIN_SCREEN_SUBHEADER);
    }

    public void showLoginSuccess(String username, String role) {
        System.out.println(Constant.LOGIN_SUCCESS_PREFIX + username + Constant.LOGIN_ROLE_PREFIX + role);
    }

    public void showLoginFailure() {
        System.out.println(Constant.LOGIN_FAILURE_MESSAGE);
    }

    public String askRetry() {
        System.out.print(Constant.RETRY_MESSAGE);
        return scanner.nextLine().trim();
    }

    public void showExitMessage() {
        System.out.println(Constant.EXIT_MESSAGE);
    }
}
