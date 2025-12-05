package util;

/**
 * PasswordValidator meets Task 2 requirements:
 * - at least 8 characters
 * - contains at least one digit
 * - contains at least one special character
 */
public class PasswordValidator {

    /**
     * Validate password strength.
     *
     * @param pwd candidate password
     * @return true if password meets requirements
     */
    public static boolean isValid(String pwd) {
        if (pwd == null || pwd.length() < 8) return false;
        boolean hasDigit = false, hasSpecial = false;
        String specials = "!@#$%^&*()_+-=[]{};:'\"\\|,.<>?/";
        for (char c : pwd.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (specials.indexOf(c) >= 0) hasSpecial = true;
            if (hasDigit && hasSpecial) return true;
        }
        return false;
    }
}


