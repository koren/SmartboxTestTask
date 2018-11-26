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
import software.smartbox.smartboxtesttask.data.EItemType;
import software.smartbox.smartboxtesttask.data.Item;


@Singleton
public class Repository {

    private final Executor executor;
    private final SmartboxService smartboxService;
    private MutableLiveData<List<Item>> events = new MutableLiveData<>();
    private MutableLiveData<List<Item>> shops = new MutableLiveData<>();

    @Inject
    Repository(SmartboxService smartboxService, Executor executor) {
        this.smartboxService = smartboxService;
        this.executor = executor;
    }

    public LiveData<List<Item>> getEvents() {
        return events;
    }

    public LiveData<List<Item>> getShops() {
        return shops;
    }

    public void refreshData() {
        executor.execute(() ->
                smartboxService.downloadFileWithFixedUrl().enqueue(new Callback<List<Item>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Item> eventList = new ArrayList<>();
                            ArrayList<Item> shopList = new ArrayList<>();
                            for (Item item : response.body()) {

                                if (item.getType() == null)
                                    continue;

                                if (item.getType().equals(EItemType.Event.toString())) {
                                    eventList.add(item);
                                } else if (item.getType().equals(EItemType.Shop.toString())) {
                                    shopList.add(item);
                                }
                            }
                            events.postValue(eventList);
                            shops.postValue(shopList);
                        }  //todo implement alert in else

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
                        //todo implement alert
                    }
                })
        );
    }
}
