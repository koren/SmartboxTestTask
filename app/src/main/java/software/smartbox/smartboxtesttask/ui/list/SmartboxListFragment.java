package software.smartbox.smartboxtesttask.ui.list;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import software.smartbox.smartboxtesttask.R;
import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.ListFragmentBinding;
import software.smartbox.smartboxtesttask.ui.details.SmartboxDetailsActivity;

public class SmartboxListFragment extends Fragment implements Callback {
    private static final String TYPE = "TYPE";
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private ListFragmentBinding binding;
    private ELocationType type = ELocationType.Event;

    public static SmartboxListFragment newInstance(ELocationType type) {
        SmartboxListFragment fragment = new SmartboxListFragment();

        Bundle args = new Bundle();
        args.putInt(TYPE, type.ordinal());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = ELocationType.values()[getArguments().getInt(TYPE)];
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        initRecyclerView(binding.getRoot());

        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);

        SmartboxListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(SmartboxListViewModel.class);
        Objects.requireNonNull(viewModel.adapter.get()).callback = this;
        viewModel.prepare(type);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onLocationSelected(Location location) {
        Intent intent = SmartboxDetailsActivity.newIntent(getContext(), location.getId(), type);
        startActivity(intent);
    }
}
