import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Expense manager.
 */
public class ExpenseManager {
    /**
     * The list of all expenses.
     */
    private final List<Expense> expenses = new ArrayList<>();

    /**
     * Add expense.
     *
     * @param description the description
     * @param amount      the amount
     */
    public void addExpense(final String description, final double amount) {
        expenses.add(new Expense(description, amount));
    }

    /**
     * Remove expense boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public boolean removeExpense(final String description) {
        boolean found = false;
        final Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            final Expense expense = iterator.next();
            if (expense.getDescription().equals(description)) {
                iterator.remove();  //Safely remove the item using the iterator
                found = true;
            }
        }
        return found;
    }

    /**
     * Print all expenses.
     */
    public void printAllExpenses() {
        for (final Expense expense : expenses) {
            System.out.println(expense.getDescription() + ": " + expense.getPrice() + " zł");
        }
    }

    /**
     * Get total expenses double.
     *
     * @return the double
     */
    public double getTotalExpenses() {
        double total = 0;
        for (final Expense expense : expenses) {
            total += expense.getPrice();
        }
        return total;
    }
}
