package budget;

import security.PasswordValidator;
import java.util.Scanner;

/**
 * The main entry point for the budget tracker application.
 * <p>
 * This class initializes the password validation process and launches the
 * {@link BudgetManager} once the user provides a valid password.
 */
public class Main {

    /**
     * Launches the application. Prompts the user to enter a valid password,
     * then initializes and runs the {@link BudgetManager}.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your personal budget tracker.");
        System.out.print("Create or enter your password: ");
        System.out.flush();

        String password = "";

        while (true) {
            if (!scanner.hasNextLine()) {
                System.out.println("\nNo input detected. Exiting.");
                return;
            }
            password = scanner.nextLine().trim();

            if (PasswordValidator.isValid(password)) break;

            System.out.println(
                    "Enter a valid password. (At least 8 chars, upper & lower case, digit, special char)"
            );
            System.out.print("Create or enter your password: ");
            System.out.flush();
        }

        System.out.println("Password accepted. Access granted!\n");

        BudgetManager manager = new BudgetManager(scanner);
        manager.run();
    }
}