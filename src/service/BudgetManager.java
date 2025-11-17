package service;

import model.Budget;
import model.Expense;
import model.Income;
import util.InputValidator;

import java.util.Scanner;

/**
 * Manages the budget operations such as adding income and expenses.
 */
public class BudgetManager {
    private Budget budget;

    public BudgetManager() {
        budget = new Budget();
    }

    public void addIncome(Scanner scanner) {
        System.out.print("Enter income source: ");
        String source = scanner.nextLine();
        System.out.print("Enter income amount: ");
        double amount = InputValidator.getDouble(scanner);
        budget.addIncome(new Income(source, amount));
        System.out.println("Income added successfully.");
    }

    public void addExpense(Scanner scanner) {
        System.out.print("Enter expense name: ");
        String name = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = InputValidator.getDouble(scanner);
        budget.addExpense(new Expense(name, amount));
        System.out.println("Expense added successfully.");
    }

    public void viewSummary() {
        System.out.println("\n--- Budget Summary ---");
        System.out.println("Incomes:");
        for (Income income : budget.getIncomes()) System.out.println(income);
        System.out.println("Expenses:");
        for (Expense expense : budget.getExpenses()) System.out.println(expense);
        System.out.println("Total Income: $" + budget.getTotalIncome());
        System.out.println("Total Expense: $" + budget.getTotalExpense());
        System.out.println("Balance: $" + budget.getBalance());
    }
}
