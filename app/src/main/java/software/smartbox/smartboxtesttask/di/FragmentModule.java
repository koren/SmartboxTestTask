package software.smartbox.smartboxtesttask.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.smartbox.smartboxtesttask.ui.details.SmartboxDetailsFragment;
import software.smartbox.smartboxtesttask.ui.list.SmartboxListFragment;

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract SmartboxListFragment contributeSmartboxListFragment();

    @ContributesAndroidInjector
    abstract SmartboxDetailsFragment contributeSmartboxDetailsFragment();
}
