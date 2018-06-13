//package com.example.osos.apprecycler;
//
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.support.v4.app.FragmentActivity;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.places.AutocompleteFilter;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
//import com.google.android.gms.location.places.ui.PlaceSelectionListener;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.LocationSource;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//    private PlaceAutocompleteFragment placeAutocompleteFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//
//
//        placeAutocompleteFragment = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        placeAutocompleteFragment.setFilter(new AutocompleteFilter.Builder().setCountry("ID").build());
//
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        float zoom = 13;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(28.127492, 30.7406567);
//        LatLng sydney1 = new LatLng(28.227492, 30.6406567);
//        LatLng sydney2 = new LatLng(28.327492, 30.5406567);
//        LatLng sydney3 = new LatLng(28.427492, 30.4406567);
////        LatLng latLng = new LatLng(locationManager.getLatitude(), location.getLongitude());
//
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Head Quarter"));
//        mMap.addMarker(new MarkerOptions().position(sydney1).title("Office1"));
//        mMap.addMarker(new MarkerOptions().position(sydney2).title("Office2"));
//        mMap.addMarker(new MarkerOptions().position(sydney3).title("Office3"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoom));
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                Intent markerIntent= new Intent(MapsActivity.this,SendActivity.class);
//                markerIntent.putExtra("name",marker.getTitle());
//                startActivity(markerIntent);
//            }
//        });
//    }
//}
