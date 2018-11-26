package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.ViewModel;

import software.smartbox.smartboxtesttask.data.Location;


public class ListItemViewModel extends ViewModel {
    public Location location;

    public ListItemViewModel(Location location) {
        this.location = location;
    }
}
