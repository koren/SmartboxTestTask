package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import javax.inject.Inject;

import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.repository.Repository;

public class SmartboxListViewModel extends ViewModel {
    private final Repository repository;
    public ObservableField<ListAdapter> adapter = new ObservableField<>();
    public ObservableArrayList<Location> data = new ObservableArrayList<>();

    @Inject
    public SmartboxListViewModel(Repository repository) {
        this.repository = repository;
        adapter.set(new ListAdapter(new ListItemDiffCallback()));
    }

    void prepare(ELocationType type) {
        data = repository.getLocations(type);
    }
}
