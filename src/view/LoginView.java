package view;

import java.util.Scanner;
import utils.*;

public class LoginView {
    private Scanner scanner = new Scanner(System.in);
    private String companyName;

    /*
     *********************************************************
     * @Constructor : LoginView()
     * 
     * @description : Default constructor - no company name set.
     ********************************************************
     */
    public LoginView() {
    }

    /*
     *********************************************************
     * @Constructor : LoginView(String companyName)
     * 
     * @description : Constructor with company name to display on login screen.
     * 
     * @param : String companyName - name of the company to show in header
     ********************************************************
     */
    public LoginView(String companyName) {
        this.companyName = companyName;
    }

    /*
     *********************************************************
     * @Method Name : getUsername
     * 
     * @description : Prompts user to enter username.
     * 
     * @return : String - entered username (raw)
     ********************************************************
     */
    public String getUsername() {
        System.out.print(Constant.ENTER_USERNAME);
        return scanner.nextLine();
    }

    /*
     *********************************************************
     * @Method Name : getPassword
     * 
     * @description : Prompts user to enter password.
     * 
     * @return : String - entered password (raw)
     ********************************************************
     */
    public String getPassword() {
        System.out.print(Constant.ENTER_PASSWORD);
        return scanner.nextLine();
    }

    /*
     *********************************************************
     * @Method Name : showLoginScreen
     * 
     * @description : Clears console screen and displays the login screen header and
     * subheader.
     ********************************************************
     */
    public void showLoginScreen() {
        ClearScreen.getInstance().clearScreen();
        System.out.println(String.format(Constant.LOGIN_SCREEN_HEADER, companyName));
        System.out.println(Constant.LOGIN_SCREEN_SUBHEADER);
    }

    /*
     *********************************************************
     * @Method Name : showLoginSuccess
     * 
     * @description : Displays a successful login message with username and role.
     * 
     * @param : String username - logged in username
     * 
     * @param : String role - role of the logged-in user
     ********************************************************
     */
    public void showLoginSuccess(String username, String role) {
        System.out.println(Constant.LOGIN_SUCCESS_PREFIX + username + Constant.LOGIN_ROLE_PREFIX + role);
    }

    /*
     *********************************************************
     * @Method Name : showLoginFailure
     * 
     * @description : Displays login failure message.
     ********************************************************
     */
    public void showLoginFailure() {
        System.out.println(Constant.LOGIN_FAILURE_MESSAGE);
    }

    /*
     *********************************************************
     * @Method Name : askRetry
     * 
     * @description : Asks the user if they want to retry login after failure.
     * 
     * @return : String - trimmed user input response (e.g., "yes" or "no")
     ********************************************************
     */
    public String askRetry() {
        System.out.print(Constant.RETRY_MESSAGE);
        return scanner.nextLine().trim();
    }

    /*
     *********************************************************
     * @Method Name : showExitMessage
     * 
     * @description : Displays a goodbye or exit message when application
     * terminates.
     ********************************************************
     */
    public void showExitMessage() {
        System.out.println(Constant.EXIT_MESSAGE);
    }
}
