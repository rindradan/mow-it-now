package fr.publicis.sapient.model;

import java.util.Arrays;

// keep the order to compute easily the direction change
public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    ;

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Direction fromValue(String value) {
        if (value == null) throw new IllegalArgumentException("Cannot accept \"null\" argument");
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The specified direction: \""+ value +"\" is not supported."));
    }
}
