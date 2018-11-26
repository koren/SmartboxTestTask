package software.smartbox.smartboxtesttask;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import software.smartbox.smartboxtesttask.data.ELocationType;
import software.smartbox.smartboxtesttask.ui.list.SmartboxListFragment;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Android World");
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Events"));
        tabs.addTab(tabs.newTab().setText("Shops"));

        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SmartboxListFragment.newInstance(ELocationType.Event), ELocationType.Event.presentation);
        adapter.addFragment(SmartboxListFragment.newInstance(ELocationType.Shop), ELocationType.Shop.presentation);
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);
    }
}

