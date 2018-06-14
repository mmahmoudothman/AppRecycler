package com.example.osos.apprecycler;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    //variable
    private static final int PERMISSION_REQUEST_CODE = 7001;
    private static final int PLAY_SERVICE_REQUEST = 7002;

    private static final int UPDATE_INTERVAL = 5000;//5 detik
    private static final int FASTEST_INTERVAL = 3000;//3detik
    private static final int DISPLACEMENT = 10;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

    private PlaceAutocompleteFragment placeAutocompleteFragment;

    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.setFilter(new AutocompleteFilter.Builder().setCountry("EG").build());

        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                final LatLng latLngLoc = place.getLatLng();

                if (marker != null) {
                    marker.remove();
                }
//                marker = mMap.addMarker(new MarkerOptions().position(latLngLoc).title(place.getName().toString()));
//                mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(place.getLatLng().latitude, place.getLatLng().longitude), 12.0f));

            }

            @Override
            public void onError(Status status) {
                Toast.makeText(Maps.this, "" + status.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setUpLocation();
    }

    private void setUpLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST_CODE);
        } else {
            if (checkPlayServices()) {
                buildGoogleApiClient();
                createLocationRequest();
                displayLocation();
            }
        }
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation != null) {
            final double latitude = mLocation.getLatitude();
            final double longitude = mLocation.getLongitude();


            //show marker
//            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("your position"));
            //Animate camera to your position
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));
        }
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICE_REQUEST).show();
            else {
                Toast.makeText(this, "This device is not supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String Snipeet1 ="Address: Minia, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet2 ="Address: Samalott, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet3 ="Address: matty, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet4 ="Address: bine mzar, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet5 ="Address: maghagha, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet6 ="Address: El-Edwaa, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet7 ="Address: Abo gorgas, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet8 ="Address: Malwy, Caro, Egypt "+"\n"+"Phone: 01129059541";
        String Snipeet9 ="Address: Deer moas, Caro, Egypt "+"\n"+"Phone: 01129059541";







        LatLng Minia = new LatLng(28.127492, 30.7406567);
        LatLng samloot = new LatLng(28.2978133, 30.7204109);
        LatLng matay = new LatLng(28.4225735, 30.7816566);
        LatLng bineMazar = new LatLng(28.491699, 30.8113703);
        LatLng maghagha = new LatLng(28.6412358, 30.8510532);
//        LatLng elEdowaa = new LatLng(28.491699, 30.8113703);
        LatLng elEdowaa= new LatLng(28.697852, 30.7768528);
        LatLng aboGrgas= new LatLng(27.9307818, 30.8468215);
        LatLng malwa = new LatLng(27.7327596, 30.8484146);
        LatLng deerMoas  = new LatLng(27.6376496, 30.859824);
//        LatLng latLng = new LatLng(locationManager.getLatitude(), location.getLongitude());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions().position(Minia).title("المنيا").snippet(Snipeet1));
        mMap.addMarker(new MarkerOptions().position(samloot).title("سمالوط").snippet(Snipeet2));
        mMap.addMarker(new MarkerOptions().position(matay).title("مطاي").snippet(Snipeet3));
        mMap.addMarker(new MarkerOptions().position(bineMazar).title("بني مزار").snippet(Snipeet4));
        mMap.addMarker(new MarkerOptions().position(maghagha).title("مغاغه").snippet(Snipeet5));
        mMap.addMarker(new MarkerOptions().position(elEdowaa).title("العدوة").snippet(Snipeet6));
        mMap.addMarker(new MarkerOptions().position(aboGrgas).title("أبوقرقاص").snippet(Snipeet7));
        mMap.addMarker(new MarkerOptions().position(malwa).title("ملوي").snippet(Snipeet8));
        mMap.addMarker(new MarkerOptions().position(deerMoas).title("دير مواس").snippet(Snipeet9));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Minia,12));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                final String title=marker.getTitle();

                AlertDialog alertDialog = new AlertDialog.Builder(Maps.this).create();
                alertDialog.setTitle(marker.getTitle());
                alertDialog.setMessage(marker.getSnippet());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();

                Intent markerIntent= new Intent(Maps.this,SendActivity.class);
                markerIntent.putExtra("name",title);
                startActivity(markerIntent);



                            }
                        });
                alertDialog.show();





//                Intent markerIntent= new Intent(Maps.this,SendActivity.class);
//                markerIntent.putExtra("name",marker.getTitle());
//                startActivity(markerIntent);
//                finish();
            }
        });



    }

    //Oh iya karena kita menggunakan permission kita override method onPermissionRequestResult


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(checkPlayServices())
                    {
                        buildGoogleApiClient();
                        createLocationRequest();
                        displayLocation();
                    }
                }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
        displayLocation();
    }
}