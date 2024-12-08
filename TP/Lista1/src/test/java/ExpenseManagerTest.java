import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Expense manager test.
 */
public class ExpenseManagerTest {

    private ExpenseManager expenseManager;

    /**
     * Sets up the expense manager before each test.
     */
    @BeforeEach
    public void setUp() {
        expenseManager = new ExpenseManager();
    }

    /**
     * Test add expense.
     */
    @Test
    public void testAddExpense() {
        expenseManager.addExpense("Groceries", 100.0);
        expenseManager.addExpense("Fuel", 50.0);

        assertEquals(150.0, expenseManager.getTotalExpenses(), 0.01);
    }

    /**
     * Test remove existing expense.
     */
    @Test
    public void testRemoveExpense_Existing() {
        expenseManager.addExpense("Groceries", 100.0);
        expenseManager.addExpense("Fuel", 50.0);

        assertTrue(expenseManager.removeExpense("Groceries"));
        assertEquals(50.0, expenseManager.getTotalExpenses(), 0.01);
    }

    /**
     * Test remove non existing expense.
     */
    @Test
    public void testRemoveExpense_NonExisting() {
        expenseManager.addExpense("Groceries", 100.0);
        expenseManager.addExpense("Fuel", 50.0);

        assertFalse(expenseManager.removeExpense("Rent"));
        assertEquals(150.0, expenseManager.getTotalExpenses(), 0.01);
    }

    /**
     * Test get total expenses.
     */
    @Test
    public void testGetTotalExpenses() {
        expenseManager.addExpense("Groceries", 100.0);
        expenseManager.addExpense("Fuel", 50.0);
        expenseManager.addExpense("Internet", 75.0);

        assertEquals(225.0, expenseManager.getTotalExpenses(), 0.01);
    }

    /**
     * Test print all expenses.
     */
    @Test
    public void testPrintAllExpenses() {
        expenseManager.addExpense("Groceries", 100.0);
        expenseManager.addExpense("Fuel", 50.0);
        expenseManager.addExpense("Internet", 75.0);

        // Simply verify that the method does not throw any exceptions
        assertDoesNotThrow(() -> expenseManager.printAllExpenses());
    }
}
