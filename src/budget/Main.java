package budget;

import security.PasswordValidator;
import java.util.Scanner;

/**
 * Main application entry. Demonstrates constants, Scanner usage, JavaDocs.
 */
public class Main {

    private static final String APP_NAME = "Personal Budget Tracker";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
System.out.print("demo");
        System.out.println("Welcome to " + APP_NAME + ".");
        System.out.print("Create or enter your password: ");
        System.out.flush();

        String password = "";

        while (true) {
            if (!scanner.hasNextLine()) {
                System.out.println("\nNo input detected. Exiting.");
                scanner.close();
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

        scanner.close();
    }
}
