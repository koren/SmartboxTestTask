package software.smartbox.smartboxtesttask.ui.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import software.smartbox.smartboxtesttask.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private double latitude;
    private double longitude;

    public static Intent newIntent(Context context, String latitude, String longitude) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.map);

        if (getIntent() != null) {
            latitude = Double.parseDouble(getIntent().getStringExtra(LATITUDE));
            longitude = Double.parseDouble(getIntent().getStringExtra(LONGITUDE));
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng newLatLng = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(newLatLng).title("Marker"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 12.0f));
    }
}
