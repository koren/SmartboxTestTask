package software.smartbox.smartboxtesttask.repository;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import software.smartbox.smartboxtesttask.api.SmartboxService;
import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;


@Singleton
public class Repository {

    private final Executor executor;
    private final SmartboxService smartboxService;
    private ObservableArrayList<Location> events = new ObservableArrayList<>();
    private ObservableArrayList<Location> shops = new ObservableArrayList<>();

    @Inject
    Repository(SmartboxService smartboxService, Executor executor) {
        this.smartboxService = smartboxService;
        this.executor = executor;
    }

    public void refreshData() {
        Log.d("TAG", "refreshData: ");
        executor.execute(() ->
                smartboxService.downloadFileWithFixedUrl().enqueue(new Callback<List<Location>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Location>> call, @NonNull Response<List<Location>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            for (Location item : response.body()) {

                                if (item.getType() == null)
                                    continue;

                                if (item.getType().equals(ELocationType.Event.toString())) {
                                    events.add(item);
                                } else if (item.getType().equals(ELocationType.Shop.toString())) {
                                    shops.add(item);
                                }
                            }
                        }  //todo implement alert in else
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Location>> call, @NonNull Throwable t) {
                        //todo implement alert
                        refreshData();
                    }
                })
        );
    }

    public Location getLocation(int locationId, ELocationType type) {
        List<Location> locations = null;
        switch (type) {
            case Event:
                locations = events;
                break;
            case Shop:
                locations = shops;
                break;
        }
        if (locations != null)
            for (Location location : locations)
                if (location.getId().equals(locationId))
                    return location;
        return null;
    }

    public ObservableArrayList<Location> getLocations(ELocationType type) {
        switch (type) {
            case Event:
                return events;
            case Shop:
                return shops;
        }
        return null;
    }
}
