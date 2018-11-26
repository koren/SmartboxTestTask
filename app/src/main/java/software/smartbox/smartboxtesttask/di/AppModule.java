package software.smartbox.smartboxtesttask.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import software.smartbox.smartboxtesttask.api.SmartboxService;

@Module(includes = ViewModelModule.class)
public class AppModule {

    //    private static String BASE_URL = "http://smartbox.software/";
//    private static String BASE_URL = "http://onekoren.000webhostapp.com/";
    private static String BASE_URL = "http://192.168.0.100:12345/";

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    SmartboxService provideApiSmartboxService(Retrofit restAdapter) {
        return restAdapter.create(SmartboxService.class);
    }
}
