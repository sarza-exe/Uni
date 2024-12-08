import java.util.Scanner;

/**
 * The type Main.
 */
public final class Main {

    /**
     * throws InstantiationError.
     */
    private Main() throws InstantiationError {
        throw new InstantiationError("This is a static class");
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        final ExpenseManager manager = new ExpenseManager();
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            final String input = scanner.nextLine();
            final String[] command = input.split(" ");

            if (command.length == 0 || command[0].isEmpty()) {
                System.out.println("Program terminated.");
                scanner.close();
                System.exit(0);
            }

            if ("add".equals(command[0])) {
                if (command.length == 3) {
                    final String description = command[1];
                    try {
                        final double price = Double.parseDouble(command[2]);
                        manager.addExpense(description, price);
                        System.out.println("Expense added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price for: " + command[2]);
                    }
                } else {
                    System.out.println("Wrong number of arguments. add [description] [price]");
                }
            } else if ("remove".equals(command[0])) {
                if (command.length == 2) {
                    final boolean found = manager.removeExpense(command[1]);
                    if (found) {
                        System.out.println("Expense removed successfully.");
                    } else {
                        System.out.println("Expense " + command[1] + " not found.");
                    }
                } else {
                    System.out.println("Wrong number of arguments. remove [description]");
                }
            } else if ("print".equals(command[0])) {
                if (command.length == 1) {
                    manager.printAllExpenses();
                } else {
                    System.out.println("Wrong number of arguments.");
                }

            } else if ("total".equals(command[0])) {
                if (command.length == 1) {
                    System.out.println("Total expenses: " + manager.getTotalExpenses() + " zł");
                } else {
                    System.out.println("Wrong number of arguments.");
                }

            } else {
                System.out.println("Wrong command.");
                System.out.println("Try add, remove, print or total.");
            }

        }
    }
}
