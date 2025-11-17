package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a personal budget including income and expenses.
 */
public class Budget {
    private List<Income> incomes;
    private List<Expense> expenses;

    public Budget() {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
    }

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public double getTotalIncome() {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getTotalExpense() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}

