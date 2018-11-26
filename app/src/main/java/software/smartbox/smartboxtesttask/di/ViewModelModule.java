package software.smartbox.smartboxtesttask.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import software.smartbox.smartboxtesttask.ui.details.SmartboxDetailsViewModel;
import software.smartbox.smartboxtesttask.ui.list.SmartboxListViewModel;
import software.smartbox.smartboxtesttask.viewmodel.FactoryViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SmartboxListViewModel.class)
    abstract ViewModel bindSmartboxListViewModel(SmartboxListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SmartboxDetailsViewModel.class)
    abstract ViewModel bindSmartboxDetailsViewModel(SmartboxDetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
