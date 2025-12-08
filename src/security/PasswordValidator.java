package security;

/**
 * Provides static utility methods for validating password strength based on
 * length, uppercase and lowercase usage, digits, and special characters.
 */
public class PasswordValidator {

    /**
     * Validates whether the given password meets all required security
     * conditions:
     * <ul>
     *     <li>Minimum length of 8 characters</li>
     *     <li>Contains at least one uppercase letter</li>
     *     <li>Contains at least one lowercase letter</li>
     *     <li>Contains at least one digit</li>
     *     <li>Contains at least one special character</li>
     * </ul>
     *
     * @param password the password to validate
     * @return {@code true} if the password meets all criteria; otherwise {@code false}
     */
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