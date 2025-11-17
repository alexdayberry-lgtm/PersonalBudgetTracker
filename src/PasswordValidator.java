import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        if (isStrongPassword(password)) {
            System.out.println("Password is strong.");
        } else {
            System.out.println("Password does not meet the required conditions.");
        }

        input.close();
    }
    public static boolean isStrongPassword(String password) {
        // Check length
        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialChars = "!@#$%^&*()_+-=[]{};:'\"\\|,.<>?/";

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (specialChars.indexOf(ch) != -1) {
                hasSpecial = true;
            }
        }

        return hasDigit && hasSpecial;
    }
}
