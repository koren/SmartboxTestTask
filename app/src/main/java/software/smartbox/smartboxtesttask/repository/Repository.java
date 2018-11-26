package software.smartbox.smartboxtesttask.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
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
    private MutableLiveData<List<Location>> events = new MutableLiveData<>();
    private MutableLiveData<List<Location>> shops = new MutableLiveData<>();

    @Inject
    Repository(SmartboxService smartboxService, Executor executor) {
        this.smartboxService = smartboxService;
        this.executor = executor;
    }

    public LiveData<List<Location>> getEvents() {
        return events;
    }

    public LiveData<List<Location>> getShops() {
        return shops;
    }

    public void refreshData() {
        executor.execute(() ->
                smartboxService.downloadFileWithFixedUrl().enqueue(new Callback<List<Location>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Location>> call, @NonNull Response<List<Location>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Location> eventList = new ArrayList<>();
                            ArrayList<Location> shopList = new ArrayList<>();
                            for (Location item : response.body()) {

                                if (item.getType() == null)
                                    continue;

                                if (item.getType().equals(ELocationType.Event.toString())) {
                                    eventList.add(item);
                                } else if (item.getType().equals(ELocationType.Shop.toString())) {
                                    shopList.add(item);
                                }
                            }
                            events.postValue(eventList);
                            shops.postValue(shopList);
                        }  //todo implement alert in else

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Location>> call, @NonNull Throwable t) {
                        //todo implement alert
                    }
                })
        );
    }
}
