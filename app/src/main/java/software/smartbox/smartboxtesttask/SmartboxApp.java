package software.smartbox.smartboxtesttask;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import software.smartbox.smartboxtesttask.di.DaggerAppComponent;
import software.smartbox.smartboxtesttask.repository.Repository;

public class SmartboxApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "onCreate: ");
        DaggerAppComponent.builder().application(this).build().inject(this);
        repository.refreshData();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
