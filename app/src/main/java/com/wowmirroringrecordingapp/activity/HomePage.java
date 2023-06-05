package com.wowmirroringrecordingapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.wowmirroringrecordingapp.R;
import com.wowmirroringrecordingapp.fragment.HomeFragment;

import java.util.Objects;

public class HomePage extends AppCompatActivity {
    AppCompatImageView burger_menue;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle actionToggle;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

        burger_menue = (AppCompatImageView) findViewById(R.id.burger_menue);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);


        actionToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionToggle);
        actionToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        type = getIntent().getStringExtra("type");
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawerLayout.closeDrawers();
        burger_menue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.bluetooth_remote:
                        Intent intent = new Intent(HomePage.this, BluetoothRemote.class);
                        startActivity(intent);
                        break;
                    case R.id.link_to_WOW_screens:
                        startActivity(new Intent("android.settings.CAST_SETTINGS"));
                        /*Intent intent1 = new Intent(HomePage.this, LinkToWOWScreens.class);
                        startActivity(intent1);*/
                        break;
                    case R.id.start_screen_mirroring_recording:
                        Intent intent1 = new Intent(HomePage.this, StartScreenMirroringRecording.class);
                        startActivity(intent1);
                        break;
                    default:
                        navigatePage(new HomeFragment(), item.getTitle().toString());
                }
                return false;
            }
        });
        navigatePage(new HomeFragment(), "TransWorld");
        setHeader();
    }

    public void navigatePage(final Fragment fragment, final String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        setTitle(title);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void setHeader() {
        final View headerView = navigationView.getHeaderView(0);
        /*AppCompatTextView usernameTextView = (AppCompatTextView) headerView.findViewById(R.id.headerNameTV);
        AppCompatTextView headerName1TV = (AppCompatTextView) headerView.findViewById(R.id.headerName1TV);

        usernameTextView.setText(sharedPreferenceClass.get("name"));
        headerName1TV.setText(sharedPreferenceClass.get("email"));*/
    }
}