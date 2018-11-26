package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.ViewModel;

import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;


public class ListItemViewModel extends ViewModel {
    private final ELocationType type;
    public Location location;

    public ListItemViewModel(Location location, ELocationType type) {
        this.location = location;
        this.type = type;
    }
}
