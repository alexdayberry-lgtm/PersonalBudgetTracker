package security;

/**
 * Validates passwords based on length, digit, and special character.
 */
public class PasswordValidator {

    public static boolean isValid(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecial = false;

        String special = "!@#$%^&*()_+-=[]{};:'\"\\|,.<>?/";

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (special.indexOf(c) != -1) hasSpecial = true;
        }

        return hasDigit && hasSpecial;
    }
}
