package utils;

import java.util.regex.Pattern;

public class Validators {

    public static boolean isValidCompanyName(String input) {
        return Pattern.matches("^[A-Za-z]{2,}( [A-Za-z]{2,}){0,24}$", input);
    }

    public static boolean isValidName(String input) {
        return Pattern.matches("^[A-Za-z ]{2,30}$", input);
    }

    public static boolean isValidUsername(String input) {
        return Pattern.matches("^(?!\\d+$)(?!_+$)[a-zA-Z0-9_]{4,20}$", input);
    }

    public static boolean isValidPassword(String input) {
        return Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,20}$", input);
    }

    public static boolean isValidEmail(String input) {
        return Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$", input);
    }

}
