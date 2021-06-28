package com.example.googlemapfinal;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapfinal.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.InfoWindowAdapter {

    private Button bt; // Mon bouton
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Récupère le fragment représentant la map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // récupère la carte et fait une synchronisation
        mapFragment.getMapAsync(this);

        bt = findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    // Où  la carte souvre par défaut
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; // Le pointeur vers notre map
        LatLng bruxelles = new LatLng(50.850340, 4.351710);
        mMap.addMarker(new MarkerOptions().position(bruxelles).title("Marker à Bruxelles"));
        // Centrer la carte sur notre point. Animatecamera pour que le mouvement soit plus fluide
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bruxelles));

        // Pour styliser la cadre qui apparait au dessus de mon maker
        mMap.setInfoWindowAdapter(this);

    }

    @Override
    // Quand je clique sur mon bouton "ajouter" il va sur l'autre pointeur
    public void onClick(View v) {
        if (v == bt) {
            // Ajoute le premier marker sur Bruxelles
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker à Sydney"));
            // Centrer la carte sur notre point. Animatecamera pour que le mouvement soit plus fluide
            mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }

    // Styliser les cadre qui apparait au dessus de mon maker ..............
    @Override
    // La vue que je veux retourner. L'ensemble ( le maker + cadre blanc )
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    // que le cadre blanc
    public View getInfoContents(Marker marker) {
        return null;
    }
}