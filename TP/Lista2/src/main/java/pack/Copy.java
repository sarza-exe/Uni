package pack;

/**
 * The type Copy.
 */
// High Cohesion: focuses only on copy related tasks
class Copy {
    /**
     * The type book.
     */
    private final Book book;

    /**
     * Whether the copy is borrowed or not.
     */
    private boolean isBorrowed;

    /**
     * Instantiates a new Copy.
     *
     * @param book the book
     */
    /* default */ Copy(final Book book) {
        this.book = book;
        this.isBorrowed = false;
    }

    /**
     * Is borrowed boolean.
     *
     * @return the boolean
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * Sets borrowed.
     *
     * @param borrowed the borrowed
     */
    public void setBorrowed(final boolean borrowed) {
        isBorrowed = borrowed;
    }

    /**
     * Gets book.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }
}
