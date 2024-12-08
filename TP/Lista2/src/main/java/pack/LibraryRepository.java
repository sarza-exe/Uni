package pack;

import java.util.List;
import java.util.Optional;

/**
 * The interface Library repository.
 */
// Pure Fabrication:
// Interface defines methods for database operations, allowing Library to interact with any data source without modifying its internal code.
// Protected Variations:
// Interface can implement different data storage mechanisms.
// Indirection:
// LibraryRepository serves as an abstraction layer between the Library class and the data storage mechanism
// Polymorphism:
// Interface allows for multiple types of data storage.
public interface LibraryRepository {
    /**
     * Save book.
     *
     * @param book the book
     */
    void saveBook(Book book);

    /**
     * Save reader.
     *
     * @param reader the reader
     */
    void saveReader(Reader reader);

    /**
     * Find book by title optional.
     *
     * @param title the title
     * @return the optional
     */
    Optional<Book> findBookByTitle(String title);

    /**
     * Find reader by name optional.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the optional
     */
    Optional<Reader> findReaderByName(String firstName, String lastName);

    /**
     * Gets all books.
     *
     * @return the all books
     */
    List<Book> getAllBooks();

    /**
     * Gets all readers.
     *
     * @return the all readers
     */
    List<Reader> getAllReaders();
}

