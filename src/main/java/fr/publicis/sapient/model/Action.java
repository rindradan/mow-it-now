package fr.publicis.sapient.model;

import java.util.Arrays;

public enum Action {
    TURN_RIGHT("D"),
    TURN_LEFT("G"),
    GO("A"),
    ;

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Action fromValue(String value) {
        if (value == null) throw new IllegalArgumentException("Cannot accept \"null\" argument");
        return Arrays.stream(Action.values())
                .filter(action -> action.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The specified action: \""+ value +"\" is not supported."));
    }
}
