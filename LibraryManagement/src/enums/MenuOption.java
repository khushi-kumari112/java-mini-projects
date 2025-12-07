package enums;

/**
 * Enum representing the menu options in the system.
 * Each option has a numeric value and display text.
 */
public enum MenuOption {
    ADD_BOOK(1, "Add Book"),
    VIEW_BOOKS(2, "View All Books"),
    ISSUE_BOOK(3, "Issue Book to User"),
    RETURN_BOOK(4, "Return Book"),
    DELETE_BOOK(5, "Delete Book"),
    EXIT(6, "Exit");

    private final int value;
    private final String displayText;

    MenuOption(int value, String displayText) {
        this.value = value;
        this.displayText = displayText;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayText() {
        return displayText;
    }

    /**
     * Converts numeric value to corresponding MenuOption
     * @param value the menu choice number
     * @return matching MenuOption
     * @throws IllegalArgumentException for invalid values
     */
    public static MenuOption fromValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid menu option: " + value);
    }
}