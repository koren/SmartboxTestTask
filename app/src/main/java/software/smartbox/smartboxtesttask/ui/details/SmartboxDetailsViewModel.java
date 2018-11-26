package software.smartbox.smartboxtesttask.ui.details;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.repository.Repository;


public class SmartboxDetailsViewModel extends ViewModel {

    private final Repository repository;
    private Location location;

    @Inject
    public SmartboxDetailsViewModel(Repository repository) {
        this.repository = repository;
    }

    public void setLocationData(int locationId, ELocationType type) {
        location = repository.getLocation(locationId, type);
    }

    public Location getLocation() {
        return location;
    }
}
