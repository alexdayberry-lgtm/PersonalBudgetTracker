package model;

/**
 * Validates password strength according to project requirements.
 */
public class PasswordValidator {

    /**
     * Checks if the password meets strength conditions:
     * At least 8 characters, includes a digit and a special character.
     */
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*()_+-=[]{};:'\"\\|,.<>?/";

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) hasDigit = true;
            if (specialChars.indexOf(ch) != -1) hasSpecialChar = true;
            if (hasDigit && hasSpecialChar) return true;
        }

        return false;
    }
}
