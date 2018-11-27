package software.smartbox.smartboxtesttask.ui.details;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import software.smartbox.smartboxtesttask.R;
import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.data.Location;
import software.smartbox.smartboxtesttask.databinding.DetailsActivityBinding;
import software.smartbox.smartboxtesttask.ui.map.MapActivity;

public class SmartboxDetailsActivity extends AppCompatActivity {
    private static final String LOCATION_ID = "LOCATION_ID";
    private static final String TYPE = "TYPE";

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    SmartboxDetailsViewModel viewModel;
    private int locationId;
    private ELocationType type;

    public static Intent newIntent(Context context, int locationId, ELocationType type) {
        Intent intent = new Intent(context, SmartboxDetailsActivity.class);
        intent.putExtra(LOCATION_ID, locationId);
        intent.putExtra(TYPE, type.ordinal());
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        DetailsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.details_activity);
        prepareData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(type.presentation);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SmartboxDetailsViewModel.class);
        viewModel.setLocationData(locationId, type);

        binding.setViewModel(viewModel);
        binding.setClickListener(v -> {
            Location location = viewModel.getLocation();
            if (location != null && location.getLongitude() != null && location.getLatitude() != null)
                openMap(location);
            //todo impl error message when incorrect location
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void openMap(Location location) {
        Intent intent = MapActivity.newIntent(this, location.getLatitude(), location.getLongitude());
        startActivity(intent);
    }

    private void prepareData() {
        if (getIntent() != null) {
            locationId = getIntent().getIntExtra(LOCATION_ID, -1);
            type = ELocationType.values()[getIntent().getIntExtra(TYPE, -1)];
        }
    }
}
