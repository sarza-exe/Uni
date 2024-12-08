/**
 * The type Expense.
 */
public class Expense {
    /**
     * The description of an Expense.
     */
    private String description;
    /**
     * The price of an Expense.
     */
    private double price;

    /**
     * Instantiates a new Expense.
     *
     * @param description the description
     * @param price       the price
     */
    public Expense(final String description, final double price) {
        this.description = description;
        this.price = price;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(final double price) {
        this.price = price;
    }
}
