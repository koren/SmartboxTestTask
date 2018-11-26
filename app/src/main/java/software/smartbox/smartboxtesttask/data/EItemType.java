package software.smartbox.smartboxtesttask.data;

import android.support.annotation.NonNull;

public enum EItemType {
    Event("event"), Shop("shop");

    private final String name;

    EItemType(String s) {
        name = s;
    }

    @NonNull
    public String toString() {
        return this.name;
    }
}
