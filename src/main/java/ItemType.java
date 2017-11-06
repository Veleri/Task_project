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
}
