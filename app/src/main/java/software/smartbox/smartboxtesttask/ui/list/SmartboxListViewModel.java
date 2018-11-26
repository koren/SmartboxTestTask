package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.repository.Repository;

public class SmartboxListViewModel extends ViewModel {
    private final Repository repository;
    public ListAdapter adapter = new ListAdapter(new ListItemDiffCallback());

    @Inject
    public SmartboxListViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Location>> getLocation(ELocationType type) {
        switch (type) {
            case Event:
                return repository.getEvents();
            case Shop:
                return repository.getShops();
            default:
                return null;
        }
    }
}
