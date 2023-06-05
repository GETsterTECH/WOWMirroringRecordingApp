package com.wowmirroringrecordingapp.activity;

import static org.osmdroid.tileprovider.util.StorageUtils.getStorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wowmirroringrecordingapp.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.config.IConfigurationProvider;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.Objects;

public class SelectYourRegisteredBusiness extends AppCompatActivity implements View.OnClickListener {
    private AppCompatImageView gps_tracker_back;
    private MapController mapController;
    private MapView mapView;
    private AppCompatTextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IConfigurationProvider provider = Configuration.getInstance();
        provider.setUserAgentValue(getPackageName());
        provider.setOsmdroidBasePath(getStorage());
        provider.setOsmdroidTileCache(getStorage());
        setContentView(R.layout.activity_select_your_registered_business);
        Objects.requireNonNull(getSupportActionBar()).hide();

        gps_tracker_back = (AppCompatImageView) findViewById(R.id.gps_tracker_back);
        gps_tracker_back.setOnClickListener(this);

        mapView = (MapView) findViewById(R.id.mapview);
        next = (AppCompatTextView) findViewById(R.id.next);

        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setBuiltInZoomControls(true);
        mapController = (MapController) mapView.getController();
        mapController.setZoom(15);
        GeoPoint point2 = new GeoPoint(41.40338, 2.17403);
        mapController.setCenter(point2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYourRegisteredBusiness.this, RegisteredMobileNumber.class);
                startActivity(intent);
            }
        });

    }

    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == gps_tracker_back) {
            onBackPressed();
            finish();
        }
    }
}