package pack;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Command line interface.
 */
public final class CLI {

    /**
     * throws InstantiationError.
     */
    private CLI() throws InstantiationError {
        throw new InstantiationError("This is a static class");
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        final LibraryRepository libraryRepository = new InMemoryLibraryRepository();
        final Library library = new Library(libraryRepository);

        final Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("1. Add a book");
            System.out.println("2. Add a copy of a book");
            System.out.println("3. Add a reader");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Show books");
            System.out.println("7. Show readers");
            System.out.println("8. Exit");

            System.out.print("Enter command (1-8): ");

            final int choice;
            try {
                choice = scanner.nextInt();
            } catch (final InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Selected option is invalid.\n");
                continue;
            }

            scanner.nextLine();

            System.out.println();

            final String title;
            final String firstName;
            final String lastName;

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    library.addBook(title);
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    library.addCopy(title);
                    break;
                case 3:
                    System.out.print("Enter first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    lastName = scanner.nextLine();
                    library.addReader(firstName, lastName);
                    break;
                case 4:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter first name of the reader: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter last name of the reader: ");
                    lastName = scanner.nextLine();
                    library.borrowBook(title, firstName, lastName);
                    break;
                case 5:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter first name of the reader: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter last name of the reader: ");
                    lastName = scanner.nextLine();
                    library.returnBook(title, firstName, lastName);
                    break;
                case 6:
                    library.showBooks();
                    break;
                case 7:
                    library.showReaders();
                    break;
                case 8:
                    System.out.println("Closing the library");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Selected option is invalid.");
                    break;
            }

            System.out.println("Press any key to continue");
            scanner.nextLine();

        }
    }
}
