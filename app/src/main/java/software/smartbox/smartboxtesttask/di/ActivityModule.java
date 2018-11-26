package software.smartbox.smartboxtesttask.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.smartbox.smartboxtesttask.MainActivity;
import software.smartbox.smartboxtesttask.ui.details.SmartboxDetailsActivity;

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract SmartboxDetailsActivity contributeSmartboxDetailsActivity();
}
