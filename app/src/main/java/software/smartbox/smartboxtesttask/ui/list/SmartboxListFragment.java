package software.smartbox.smartboxtesttask.ui.list;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import software.smartbox.smartboxtesttask.R;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.ListFragmentBinding;

import static android.widget.GridLayout.VERTICAL;

public class SmartboxListFragment extends Fragment {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private SmartboxListViewModel viewModel;
    private List<Location> events;
    private List<Location> shops;
    private ListFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.list_fragment, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        initRecyclerView(binding.getRoot());

        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SmartboxListViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getEvents().observe(this, events ->
                {
                    this.events = events;
                    viewModel.adapter.submitList(events);
                }
        );
        viewModel.getShops().observe(this, shops ->
                this.shops = shops);
    }
}
