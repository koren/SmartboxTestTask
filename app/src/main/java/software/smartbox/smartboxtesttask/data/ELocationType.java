package software.smartbox.smartboxtesttask.data;

import android.support.annotation.NonNull;

public enum ELocationType {
    Event("event"), Shop("shop");

    private final String name;

    ELocationType(String s) {
        name = s;
    }

    @NonNull
    public String toString() {
        return this.name;
    }
}
