package software.smartbox.smartboxtesttask.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import software.smartbox.smartboxtesttask.data.Item;
import software.smartbox.smartboxtesttask.repository.Repository;

public class SmartboxListViewModel extends ViewModel {
    private final Repository repository;

    @Inject
    public SmartboxListViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<List<Item>> getEvents() {
        return repository.getEvents();
    }

    LiveData<List<Item>> getShops() {
        return repository.getShops();
    }
}
