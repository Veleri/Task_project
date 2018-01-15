/**
 * Class describes enum "ItemType"
 *
 * @author Veleri Rechembei
 * @version %I%, %G%
 *
 */
public enum ItemType {
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


    /**
     * Calculates item's discount.
     * For NEW item discount is 0%;
     * For SECOND_FREE item discount is 50% if quantity > 1
     * For SALE item discount is 70%
     * For each full 10 not NEW items item gets additional 1% discount,
     * but not more than 80% total
     */
    public static int calculateDiscount(ItemType type, int quantity) {
        int discount = 0;
        if(type.equals(ItemType.SECOND_FREE) && quantity > 1){
            discount = 50;
        }
        if(type.equals(ItemType.SALE)){
            discount = 70;
        }
        if(!type.equals(ItemType.NEW) &&  quantity >= 10 ){
            discount += quantity / 10;
            if(discount > 80){
                discount = 80;
            }
        }
        return  discount;
    }
}
