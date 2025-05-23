package view;

import java.util.Scanner;
import utils.*;

public class RegistrationView {
    Scanner sc = ScannerSingleton.getInstance();

    public String getCompanyName() {
        while (true) {
            System.out.println(Constant.ENTER_COMPANY_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidCompanyName(name)) return name;
            System.out.println(Constant.INVALID_COMPANY_NAME);
        }
    }

    public String getFirstName() {
        while (true) {
            System.out.println(Constant.ENTER_FIRST_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidName(name)) return name;
            System.out.println(Constant.INVALID_FIRST_NAME);
        }
    }

    public String getLastName() {
        while (true) {
            System.out.println(Constant.ENTER_LAST_NAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidName(name)) return name;
            System.out.println(Constant.INVALID_LAST_NAME);
        }
    }

    public String getPersonalEmail() {
        while (true) {
            System.out.print(Constant.ENTER_PERSONAL_EMAIL);
            String email = sc.nextLine().trim();
            if (Validators.isValidEmail(email)) return email;
            System.out.println(Constant.INVALID_EMAIL);
        }
    }

    public String getUsername() {
        while (true) {
            System.out.print(Constant.ENTER_USERNAME);
            String name = sc.nextLine().trim();
            if (Validators.isValidUsername(name)) return name;
            System.out.println(Constant.INVALID_USERNAME);
        }
    }

    public String getPassword() {
        while (true) {
            System.out.print(Constant.ENTER_PASSWORD);
            String name = sc.nextLine().trim();
            if (Validators.isValidPassword(name)) return name;
            System.out.println(Constant.INVALID_PASSWORD);
        }
    }

    public String getEmail() {
        while (true) {
            System.out.print(Constant.ENTER_COMPANY_EMAIL);
            String email = sc.nextLine().trim();
            if (Validators.isValidEmail(email)) return email;
            System.out.println(Constant.INVALID_EMAIL);
        }
    }

    public void showAlreadyRegisteredMessage() {
        System.out.println(Constant.COMPANY_ALREADY_REGISTERED);
    }

    public void showRegistrationSuccess() {
        System.out.println(Constant.REGISTRATION_SUCCESS);
    }


    public void showUserNameExistsMessage() {
        System.out.println(Constant.USER_NAME_ALREADY_EXISTS);
    }

    public void showEmailExistsMessage() {
        System.out.println(Constant.EMAIL_ALREADY_EXISTS);
    }

    public void showLoginFailedMessage() {
        System.out.println(Constant.LOGIN_FAILED);
    }
}
