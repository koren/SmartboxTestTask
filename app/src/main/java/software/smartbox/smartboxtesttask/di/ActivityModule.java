package software.smartbox.smartboxtesttask.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.smartbox.smartboxtesttask.MainActivity;

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
