package pack;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Book.
 */
// Information Expert principle: Manages borrowing copies of the book
// High Cohesion: focuses only on book related tasks
class Book {

    /**
     * Title of the book.
     */
    private final String title;
    /**
     * List of the copies of the book.
     */
    private final List<Copy> copies;

    /**
     * Instantiates a new Book.
     *
     * @param title the title
     */
    /* default */ Book(final String title) {
        this.title = title;
        this.copies = new ArrayList<>();
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Add copy.
     */
    public void addCopy() {
        copies.add(new Copy(this));
    }

    /**
     * Borrow copy copy.
     *
     * @return the copy
     */
    public Copy borrowCopy() {
        for (final Copy copy : copies) {
            if (!copy.isBorrowed()) {
                copy.setBorrowed(true);
                return copy;
            }
        }
        return null; // no available copies
    }

    /**
     * Return copy.
     *
     * @param copy the copy
     */
    public void returnCopy(final Copy copy) {
        copy.setBorrowed(false);
    }

    /**
     * Returns the number of copies.
     *
     * @return  int size of List<copy>
     */
    public int howManyCopies() {
        return copies.size();
    }

    /**
     * Returns copies of the book.
     *
     * @return List<copy> of the copies
     */
    public List<Copy> getCopies() {
        return copies;
    }

}


