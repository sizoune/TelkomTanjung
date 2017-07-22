package com.kp.mwi.telkomtanjung;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinsBeard;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OnMapOpen extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String namaODP;
    double latitude, langitude;
    LatLng tujuan, asal;
    Merlin merlin, merlin1;
    MerlinsBeard merlinsBeard;

    @BindView(R.id.status)
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_map_open);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            namaODP = b.getString("nama");
            getSupportActionBar().setTitle(b.getString("nama"));
            latitude = Double.parseDouble(b.getString("koord").substring(2, 10));
            langitude = Double.parseDouble(b.getString("koord").substring(13, 23));
            tujuan = new LatLng(-latitude, langitude);
        }

        merlin = new Merlin.Builder().withConnectableCallbacks().build(this);
        merlinsBeard = MerlinsBeard.from(this);
        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
            }
        });
        if (!merlinsBeard.isConnected()) {
            status.setText("Anda tidak terhubung dengan internet !");
        } else {
            klokonek();
        }
    }

    private void klokonek() {
        status.setText("Klik penanda untuk mengetahui rute !");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng posisi = new LatLng(-latitude, langitude);
        mMap.addMarker(new MarkerOptions().position(posisi).title(namaODP));
        CameraUpdate center = CameraUpdateFactory.newLatLng(posisi);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
//        mMap.setMyLocationEnabled(true);
    }

}
