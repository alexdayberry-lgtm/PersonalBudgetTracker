package budget;

import security.PasswordValidator;
import java.util.Scanner;

/**
 * Application entry point.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your personal budget tracker.");
        System.out.print("Create or enter your password: ");
        System.out.flush();

        String password = "";
        // read password safely
        while (true) {
            if (!scanner.hasNextLine()) { // protect against EOF
                System.out.println("\nNo input detected. Exiting.");
                return;
            }
            password = scanner.nextLine().trim();
            if (PasswordValidator.isValid(password)) break;
            System.out.println("Enter a valid password. (At least 8 chars, upper & lower case, digit, special char)");
            System.out.print("Create or enter your password: ");
            System.out.flush();
        }

        System.out.println("Password accepted. Access granted!\n");

        // Pass the single Scanner instance to the manager so we don't create multiple scanners
        BudgetManager manager = new BudgetManager(scanner);
        manager.run();
    }
}
