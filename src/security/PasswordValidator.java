package security;

/**
 * Provides static utility methods for validating password strength.
 * (No changes needed besides keeping it as a static utility example.)
 */
public class PasswordValidator {

    public static boolean isValid(String password) {
        if (password == null) return false;
        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialChars = "!@#$%^&*()_+-=[]{};:'\"\\\\|,.<>?/";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (specialChars.indexOf(c) != -1) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
