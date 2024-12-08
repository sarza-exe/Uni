package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type In memory library repository.
 */
public final class InMemoryLibraryRepository implements LibraryRepository {

    /**
     * list of books.
     */
    private final List<Book> books;

    /**
     * list of readers.
     */
    private final List<Reader> readers;

    /**
     * Instantiates a new In memory library repository.
     */
    public InMemoryLibraryRepository() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    @Override
    public void saveBook(final Book book) {
        books.add(book);
    }

    @Override
    public void saveReader(final Reader reader) {
        readers.add(reader);
    }

    @Override
    public Optional<Book> findBookByTitle(final String title) {
        return books.stream().filter(book ->
                book.getTitle().equalsIgnoreCase(title)).findFirst();
    }

    @Override
    public Optional<Reader> findReaderByName(final String firstName, final String lastName) {
        return readers.stream().filter(reader ->
                reader.getFullName().equalsIgnoreCase(firstName + " " + lastName)
        ).findFirst();
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public List<Reader> getAllReaders() {
        return new ArrayList<>(readers);
    }

}

