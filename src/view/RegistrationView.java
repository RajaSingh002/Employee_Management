package view;

import java.util.Scanner;
import utils.*;

public class RegistrationView {
    private final Scanner sc = ScannerSingleton.getInstance();

    /*
     *********************************************************
     * @Method Name : getCompanyName
     * 
     * @description : Prompts user to enter company name, validates input.
     * 
     * @return : Validated company name
     ********************************************************
     */
    public String getCompanyName() {
        while (true) {
            System.out.println(Constant.ENTER_COMPANY_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidCompanyName(name)) {
                return name;
            }
            System.out.println(Constant.INVALID_COMPANY_NAME);
        }
    }

    /*
     *********************************************************
     * @Method Name : getFirstName
     * 
     * @description : Prompts user to enter first name, validates input.
     * 
     * @return : Validated first name
     ********************************************************
     */
    public String getFirstName() {
        while (true) {
            System.out.println(Constant.ENTER_FIRST_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidName(name)) {
                return name;
            }
            System.out.println(Constant.INVALID_FIRST_NAME);
        }
    }

    /*
     *********************************************************
     * @Method Name : getLastName
     * 
     * @description : Prompts user to enter last name, validates input.
     * 
     * @return : Validated last name
     ********************************************************
     */
    public String getLastName() {
        while (true) {
            System.out.println(Constant.ENTER_LAST_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidName(name)) {
                return name;
            }
            System.out.println(Constant.INVALID_LAST_NAME);
        }
    }

    /*
     *********************************************************
     * @Method Name : getPersonalEmail
     * 
     * @description : Prompts user to enter personal email, validates input.
     * 
     * @return : Validated personal email
     ********************************************************
     */
    public String getPersonalEmail() {
        while (true) {
            System.out.print(Constant.ENTER_PERSONAL_EMAIL);
            String email = sc.nextLine().trim();
            if (Validators.isValidEmail(email)) {
                return email;
            }
            System.out.println(Constant.INVALID_EMAIL);
        }
    }

    /*
     *********************************************************
     * @Method Name : getUsername
     * 
     * @description : Prompts user to enter username, validates input.
     * 
     * @return : Validated username
     ********************************************************
     */
    public String getUsername() {
        while (true) {
            System.out.print(Constant.ENTER_USERNAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidUsername(name)) {
                return name;
            }
            System.out.println(Constant.INVALID_USERNAME);
        }
    }

    /*
     *********************************************************
     * @Method Name : getPassword
     * 
     * @description : Prompts user to enter password, validates input.
     * 
     * @return : Validated password
     ********************************************************
     */
    public String getPassword() {
        while (true) {
            System.out.print(Constant.ENTER_PASSWORD);
            String password = sc.nextLine().trim();
            if (Validators.isValidPassword(password)) {
                return password;
            }
            System.out.println(Constant.INVALID_PASSWORD);
        }
    }

    /*
     *********************************************************
     * @Method Name : getEmail
     * 
     * @description : Prompts user to enter company email, validates input.
     * 
     * @return : Validated company email
     ********************************************************
     */
    public String getEmail() {
        while (true) {
            System.out.print(Constant.ENTER_COMPANY_EMAIL);
            String email = sc.nextLine().trim();
            if (Validators.isValidEmail(email)) {
                return email;
            }
            System.out.println(Constant.INVALID_EMAIL);
        }
    }

    /*
     *********************************************************
     * @Method Name : showAlreadyRegisteredMessage
     * 
     * @description : Displays message if company is already registered.
     ********************************************************
     */
    public void showAlreadyRegisteredMessage() {
        System.out.println(Constant.COMPANY_ALREADY_REGISTERED);
    }

    /*
     *********************************************************
     * @Method Name : showRegistrationSuccess
     * 
     * @description : Displays successful registration message.
     ********************************************************
     */
    public void showRegistrationSuccess() {
        System.out.println(Constant.REGISTRATION_SUCCESS);
    }

    /*
     *********************************************************
     * @Method Name : showUserNameExistsMessage
     * 
     * @description : Displays message if username already exists.
     ********************************************************
     */
    public void showUserNameExistsMessage() {
        System.out.println(Constant.USER_NAME_ALREADY_EXISTS);
    }

    /*
     *********************************************************
     * @Method Name : showEmailExistsMessage
     * 
     * @description : Displays message if email already exists.
     ********************************************************
     */
    public void showEmailExistsMessage() {
        System.out.println(Constant.EMAIL_ALREADY_EXISTS);
    }

    /*
     *********************************************************
     * @Method Name : showLoginFailedMessage
     * 
     * @description : Displays a login failure message.
     ********************************************************
     */
    public void showLoginFailedMessage() {
        System.out.println(Constant.LOGIN_FAILED);
    }
}
