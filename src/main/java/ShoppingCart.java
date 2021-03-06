package java;
import java.util.*;
import java.text.*;
/**
 * Containing items and calculating price.
 */
public class ShoppingCart {

    /**
     * Container for added items
     */
    private List<Item> items = new ArrayList<Item>();
    private List<String[]> lines = new ArrayList<String[]>();
    private String[] header = {"#", "Item", "Price", "Quan.", "Discount", "Total"};
    private String[] footer;
    private int[] align = new int[]{1, -1, 1, 1, 1, 1};
    private int[] width = new int[]{0, 0, 0, 0, 0, 0};
    private int lineSize;
    private static final NumberFormat MONEY;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        MONEY = new DecimalFormat("$#.00", symbols);
    }

    /**
     * Tests all class methods.
     */
    public static void main(String[] args) {
// TODO: add tests here
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, ItemType.NEW);
        cart.addItem("Banana", 20.00, 4, ItemType.SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ItemType.SALE);
        cart.addItem("Nails", 2.00, 500, ItemType.REGULAR);
        System.out.println(cart.formatTicket());
    }

    /**
     * Adds new item.
     *
     * @param title    item title 1 to 32 symbols
     * @param price    item ptice in USD, > 0
     * @param quantity item quantity, from 1
     * @param type     item type
     * @throws IllegalArgumentException if some value is wrong
     */
    public void addItem(String title, double price, int quantity, ItemType type) {
        Item newItem  = new Item();
        newItem.setPrice(price);
        newItem.setTitle(title);
        newItem.setQuantity(quantity);
        newItem.setItemType(type);
        items.add(newItem);
    }

    /**
     * Method, that add separator.
     * @param sb - StringBuilder
     * @param lineLength - size of lines
     */
    private static void addSeparator(StringBuilder sb, int lineLength) {
        for (int i = 0; i < lineLength; i++)
            sb.append("-");
        sb.append("\n");
    }

    /**
     * Method, that prepare lines.
     */
    private void prepareLines() {
        double total = 0.00;
        int index = 0;
        for (Item item : items) {
            int discount = ItemType.calculateDiscount(item.getItemType(),item.getQuantity());
            double itemTotal = item.getPrice() * item.getQuantity() * (100.00 - discount) / 100.00;
            this.lines.add(new String[]{
                    String.valueOf(++index),
                    item.getTitle(),
                    MONEY.format(item.getPrice()),
                    String.valueOf(item.getQuantity()),
                    (discount == 0) ? "-" : (String.valueOf(discount) + "%"),
                    MONEY.format(itemTotal)
            });
            total += itemTotal;
        }
        footer = new String[]{String.valueOf(index), "", "", "", "", MONEY.format(total)};
    }

    /**
     * Method, that format lines of StringBuilder.
     * @param sb - StringBuilder
     */
    private void formatLines(StringBuilder sb) {
        for (String[] line : lines) {
            for (int i = 0; i < line.length; i++)
                appendFormatted(sb, line[i], align[i], width[i]);
            sb.append("\n");
        }
    }

    /**
     * Method, that format footer of StringBuilder.
     * @param sb - StringBuilder
     */
    private void formatFooter(StringBuilder sb) {
        addSeparator(sb, lineSize);
        for (int i = 0; i < footer.length; i++)
            appendFormatted(sb, footer[i], align[i], width[i]);
    }

    /**
     *Method, that count lines lengths.
     */
    private void countLineLength() {
        lineSize = width.length - 1;
        for (int w : width)
            lineSize += w;
    }

    /**
     * Method, that format header of StringBuilder
     * @param sb - StringBuilder
     */
    private void formatHeader(StringBuilder sb) {
        for (int i = 0; i < header.length; i++)
            appendFormatted(sb, header[i], align[i], width[i]);
        sb.append("\n");
        addSeparator(sb, lineSize);
    }

    /**
     * Method, that count columns lengths.
     */
    private void countColumnLengths() {
        for (String[] line : lines)
            for (int i = 0; i < line.length; i++)
                width[i] = (int) Math.max(width[i], line[i].length());
        for (int i = 0; i < header.length; i++)
            width[i] = (int) Math.max(width[i], header[i].length());
        for (int i = 0; i < footer.length; i++)
            width[i] = (int) Math.max(width[i], footer[i].length());
    }
    /**
     * Formats shopping price.
     *
     * @return string as lines, separated with \n,
     * first line: # Item Price Quan. Discount Total
     * second line: ---------------------------------------------------------
     * next lines: NN Title $PP.PP Q DD% $TT.TT
     * 1 Some title $.30 2 - $.60
     * 2 Some very long $100.00 1 50% $50.00
     * ...
     * 31 Item 42 $999.00 1000 - $999000.00
     * end line: ---------------------------------------------------------
     * last line: 31 $999050.60
     * <p>
     * if no items in cart returns "No items." string.
     */
    public String formatTicket() {
        if (items.size() == 0)
            return "No items.";
        StringBuilder sb = new StringBuilder();
        prepareLines();
        countColumnLengths();
        countLineLength();
        formatHeader(sb);
        formatLines(sb);
        formatFooter(sb);
        return sb.toString();
    }

    /**
     * Appends to sb formatted value.
     * Trims string if its length > width.
     *
     * @param align -1 for align left, 0 for center and +1 for align right.
     */
    public static void appendFormatted(StringBuilder sb, String value, int align, int width) {
        if (value.length() > width)
            value = value.substring(0, width);
        int before = (align == 0)
                ? (width - value.length()) / 2
                : (align == -1) ? 0 : width - value.length();
        int after = width - value.length() - before;
        while (before-- > 0)
            sb.append(" ");
        sb.append(value);
        while (after-- > 0)
            sb.append(" ");
        sb.append(" ");
    }

}