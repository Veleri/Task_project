
public class Item {

    public static enum ItemType {
        NEW,
        REGULAR,
        SECOND_FREE,
        SALE;

        /**
         * Method, that transforms String command to  preference.
         * @param command preference in String format.
         * @return preference of String message.
         * @throws IllegalArgumentException if command value is wrong
         */
        public static ItemType ItemTypeFromString(String command) {
            if (command != null || !command.isEmpty()) {
                for (ItemType preference : ItemType.values()) {
                    if (preference.name().equals(command)) {
                        return preference;
                    }
                }
            }
                throw new IllegalArgumentException("Invalid parameter value");
        }
    }

    private String title;
    private double price;
    private int quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
