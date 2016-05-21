package com.example.ensai.runplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MyLocationListener myLocationListener  = null;
    PolylineOptions polylineOpt = null;
    Polyline polyline = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        myLocationListener = new MyLocationListener();

        mapFragment.getMapAsync(this);


    }

    public void updatePosition( double latitude, double longitude){
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("Vous êtes ici"));
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        myLocationListener.update(this,this);
        LatLng here = new LatLng(myLocationListener.getLatitude() ,  myLocationListener.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(here).title("Vous êtes ici"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(48.050150,-1.741514)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.050150,-1.741514), 17.0f));
        ArrayList<Double[]> listePosLongLat = new ArrayList<>();
        listePosLongLat.add(new Double[]{-1.741514,48.050150});
        listePosLongLat.add(new Double[]{-1.742926,48.049774,});
        listePosLongLat.add(new Double[]{-1.741660,48.047866});
        listePosLongLat.add(new Double[]{-1.741113,48.048181});
        listePosLongLat.add(new Double[]{-1.740877,48.048483});
        listePosLongLat.add(new Double[]{-1.740480, 48.050362});
        listePosLongLat.add(new Double[]{-1.741349, 48.050360});
        drawPath(listePosLongLat);
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MyLocationListener.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myLocationListener.accessFineLocationPermissionGot();
                    this.updatePosition(myLocationListener.getLatitude(),myLocationListener.getLongitude());
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    myLocationListener.checkPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void drawPath(ArrayList<Double[]> listePosLongLat){
        Double[] lastpos = new Double[]{null,null};
         polylineOpt = new PolylineOptions();
        for(Double[] element : listePosLongLat){
            Double longitude = element[0];
            Double latitude = element[1];
            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(""));

            polylineOpt.add(new LatLng(latitude,longitude));
            if (lastpos[0]!=null&&lastpos[1]!=null){

            }
            lastpos=element;

        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(lastpos[1],lastpos[0])).title("Vous êtes ici"));
        polyline = mMap.addPolyline(polylineOpt);
    }
}
