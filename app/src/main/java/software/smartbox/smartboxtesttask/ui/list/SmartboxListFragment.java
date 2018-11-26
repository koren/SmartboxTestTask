package software.smartbox.smartboxtesttask.ui.list;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import software.smartbox.smartboxtesttask.R;
import software.smartbox.smartboxtesttask.data.Item;

public class SmartboxListFragment extends Fragment {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private SmartboxListViewModel viewModel;
    private List<Item> events;
    private List<Item> shops;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SmartboxListViewModel.class);
        viewModel.getEvents().observe(this, events ->
                this.events = events);
        viewModel.getShops().observe(this, shops ->
                this.shops = shops);
    }
}
