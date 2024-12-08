package pack;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test of library class.
 */
final class LibraryTest {

    /**
     * The library repository.
     */
    private LibraryRepository libraryRepository;

    /**
     * The library class.
     */
    private Library library;

    /**
     * The title of a book to be tested
     */
    private final static String TITLE = "Super Book Title";

    /**
     * Empty constructor.
     */
    private LibraryTest() {}

    /**
     * the set-up before every test.
     */
    @BeforeEach
    public void setUp() {
        libraryRepository = new InMemoryLibraryRepository();
        library = new Library(libraryRepository);
    }

    @org.junit.jupiter.api.Test
    void addBook() {
        library.addBook(TITLE);

        //Asserts if book is correctly added
        assertEquals(TITLE, libraryRepository.findBookByTitle(TITLE).get().getTitle(), "The book wasn't added correctly");
    }

    @org.junit.jupiter.api.Test
    void addCopy() {
        library.addBook(TITLE);
        library.addCopy(TITLE);

        // asserts if addCopy adds one and only one copy of a book
        assertEquals(1, libraryRepository.findBookByTitle(TITLE).get().howManyCopies(), "The copy wasn't added correctly");
    }

    @org.junit.jupiter.api.Test
    void addReader() {
        final String firstName = "John";
        final String lastName = "Doe";

        library.addReader(firstName, lastName);

        // Asserts if Reader is correctly added
        assertEquals("John Doe", libraryRepository.findReaderByName("John", "Doe").get().getFullName(), "The reader wasn't added correctly");
    }

    @org.junit.jupiter.api.Test
    void borrowBook() {
        final String firstName = "John";
        final String lastName = "Doe";

        library.addBook(TITLE);
        library.addCopy(TITLE);
        library.addReader(firstName, lastName);

        library.borrowBook(TITLE, firstName, lastName);

        // Asserts if the borrowed book is marked as borrowed
        assertTrue(libraryRepository.findReaderByName("John", "Doe").get().getBorrowedBooks().getFirst().isBorrowed());
    }

    @org.junit.jupiter.api.Test
    void returnBook() {
        final String firstName = "John";
        final String lastName = "Doe";

        library.addBook(TITLE);
        library.addCopy(TITLE);
        library.addReader(firstName, lastName);
        library.borrowBook(TITLE, firstName, lastName);

        library.returnBook(TITLE, firstName, lastName);

        assertFalse(libraryRepository.findBookByTitle(TITLE).get().getCopies().getFirst().isBorrowed());
    }
}