package software.smartbox.smartboxtesttask.data;

import android.support.annotation.NonNull;

public enum ELocationType {
    Event("event", "Events"), Shop("shop", "Shops");

    private final String name;
    public final String presentation;

    ELocationType(String name, String presentation) {
        this.name = name;
        this.presentation = presentation;
    }

    @NonNull
    public String toString() {
        return this.name;
    }
}
