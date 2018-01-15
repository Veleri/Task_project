package java;
/**
 * Class describes essence "Item"
 *
 * @author Veleri Rechembei
 * @version %I%, %G%
 *
 */
public class Item {

    private String title;
    private double price;
    private int quantity;
    private ItemType itemType;

    public Item (String title, ItemType type, double price, int quantity){
        setTitle(title);
        setItemType(type);
        setPrice(price);
        setQuantity(quantity);
    }
    /**
     * Method that get title of Item
     * @return title of Item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method that set value of title of Item
     * @param title is String title of Item
     * @throws IllegalArgumentException if title has wrong value
     */
    public void setTitle(String title) {
        if (title == null || title.length() == 0 || title.length() > 32){
            throw new IllegalArgumentException("Illegal title");
        }
        this.title = title;
    }

    /**
     * Method that get price of Item
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method that set value of price of Item
     * @param price is double price of Item
     * @throws IllegalArgumentException if price has value less than "0.01"
     */
    public void setPrice(double price) {
        if (price < 0.01){
            throw new IllegalArgumentException("Illegal price");
        }
        this.price = price;
    }

    /**
     * Method that get value of quantity of Item
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method that set value of quantity of Item
     * @param quantity is int quantity of Item
     * @throws IllegalArgumentException if price has value less than "1"
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0){
            throw new IllegalArgumentException("Illegal quantity");
        }
        this.quantity = quantity;
    }

    /**
     * Method that get value of type of Item
     * @return is ItemType type of Item
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Method that set value of type of Item
     * @param itemType is type of Item
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
