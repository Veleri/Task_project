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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
