package pack;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Reader.
 */
// Information Expert principle: Manages its own borrowed copies.
// High Cohesion: focuses only on reader related tasks
class Reader {
    /**
     * First name of the reader.
     */
    private final String firstName;

    /**
     * Last name of the reader.
     */
    private final String lastName;

    /**
     * List of borrowed books.
     */
    private final List<Copy> borrowedBooks;

    /**
     * Instantiates a new Reader.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    /* default */ Reader(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Borrow book.
     *
     * @param copy the copy
     */
    public void borrowBook(final Copy copy) {
        borrowedBooks.add(copy);
    }

    /**
     * Return book.
     *
     * @param copy the copy
     */
    public void returnBook(final Copy copy) {
        borrowedBooks.remove(copy);
    }

    /**
     * Gets borrowed books.
     *
     * @return the borrowed books
     */
    public List<Copy> getBorrowedBooks() {
        return borrowedBooks;
    }
}
