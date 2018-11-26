package software.smartbox.smartboxtesttask.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import software.smartbox.smartboxtesttask.ui.details.SmartboxDetailsActivity;
import software.smartbox.smartboxtesttask.ui.list.SmartboxListFragment;

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract SmartboxListFragment contributeSmartboxListFragment();
}
