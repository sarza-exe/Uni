package pack;

import java.util.Optional;

/**
 * The type Library.
 */
// Creator principle:
// Creator of Book and Reader instances, as it maintains the collections of both.
// Controller principle:
// Controller for system events, managing high-level operations for book and reader management.
// Low Coupling:
// Library interacts with Book and Reader through specific methods, minimizing dependency on their internal structure.
class Library {

    /**
     * Library Repository that lists all books and readers.
     */
    private final LibraryRepository repository;


    /**
     * Instantiates a new Library.
     *
     * @param repository the type of repository
     */
    //Constructor injection: Library depends on LibraryRepository abstraction.
    /* default */ Library(final LibraryRepository repository) {
        this.repository = repository;
    }

    /**
     * Add book.
     *
     * @param title the title
     */
    public void addBook(final String title) {
        Optional<Book> book = repository.findBookByTitle(title);
        if (book.isEmpty()) {
            book = Optional.of(new Book(title));
            repository.saveBook(book.get());
            System.out.println("Book added: " + title);
        } else {
            System.out.println("Book " + title + " already exists");
        }
    }

    /**
     * Add copy.
     *
     * @param title the title
     */
    public void addCopy(final String title) {
        final Optional<Book> book = repository.findBookByTitle(title);
        if (book.isPresent()) {
            book.get().addCopy();
            System.out.println("Added a copy of book \"" + title + "\"");
        } else {
            System.out.println("Book \"" + title + "\" does not exist.");
        }
    }

    /**
     * Add reader.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void addReader(final String firstName, final String lastName) {
        final Optional<Reader> reader = repository.findReaderByName(firstName, lastName);
        if (reader.isEmpty()) {
            final Reader newReader = new Reader(firstName, lastName);
            repository.saveReader(newReader);
            System.out.println("Added reader: " + firstName + " " + lastName);
        } else {
            System.out.println("Reader " + firstName + " " + lastName + " already exists");
        }
    }

    /**
     * Borrow book.
     *
     * @param title     the title
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void borrowBook(final String title, final String firstName, final String lastName) {
        final Optional<Book> book = repository.findBookByTitle(title);
        final Optional<Reader> reader = repository.findReaderByName(firstName, lastName);

        if (book.isPresent() && reader.isPresent()) {
            final Copy copy = book.get().borrowCopy();
            if (copy != null) {
                reader.get().borrowBook(copy);
                System.out.println(firstName + " " + lastName + " borrowed book \"" + title + "\"");
            } else {
                System.out.println("No copies available for book \"" + title + "\"");
            }
        } else if (reader.isEmpty()) {
            System.out.println("Reader not found.");
        } else {
            System.out.println("Book not found.");
        }

    }

    /**
     * Return book.
     *
     * @param title     the title
     * @param firstName the first name
     * @param lastName  the last name
     */
    public void returnBook(final String title, final String firstName, final String lastName) {
        final Optional<Reader> reader = repository.findReaderByName(firstName, lastName);

        if (reader.isPresent()) {
            Copy copyToReturn = null;
            for (final Copy copy : reader.get().getBorrowedBooks()) {
                if (copy.getBook().getTitle().equals(title)) {
                    copyToReturn = copy;
                    break;
                }
            }

            if (copyToReturn != null) {
                final Book book = copyToReturn.getBook();
                book.returnCopy(copyToReturn);
                reader.get().returnBook(copyToReturn);
                System.out.println(firstName + " " + lastName + " returned \"" + title + "\"");
            } else {
                System.out.println("Reader does not have the book.");
            }
        } else {
            System.out.println("Reader not found.");
        }
    }

    /**
     * Show books.
     */
    public void showBooks() {
        if (repository.getAllBooks().isEmpty()) {
            System.out.println("No books found in the library.");
        } else {
            System.out.println("Books in the library:");
            for (final Book book : repository.getAllBooks()) {
                System.out.println(book.getTitle());
            }
        }
    }

    /**
     * Show readers.
     */
    public void showReaders() {
        if (repository.getAllReaders().isEmpty()) {
            System.out.println("No readers found in the library.");
        } else {
            System.out.println("Readers in the library:");
            for (final Reader reader : repository.getAllReaders()) {
                System.out.println(reader.getFullName());
            }
        }
    }

}
