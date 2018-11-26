package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.repository.Repository;

public class SmartboxListViewModel extends ViewModel {
    private final Repository repository;
    public ListAdapter adapter = new ListAdapter(new ListItemDiffCallback());

    @Inject
    public SmartboxListViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<List<Location>> getEvents() {
        return repository.getEvents();
    }

    LiveData<List<Location>> getShops() {
        return repository.getShops();
    }
}
