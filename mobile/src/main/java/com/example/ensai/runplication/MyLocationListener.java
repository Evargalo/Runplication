package com.example.ensai.runplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

/**
 * Created by ensai on 10/05/16.
 */
public class MyLocationListener implements LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private Double longitude = (double) 0; //todo : mettre null
    private Double latitude = (double) 0; //todo : mettre null
    private Context contexte;

    @Override
    public void onLocationChanged(Location location) {

        longitude = location.getLongitude();
        String longitudetxt = "Longitude: " + longitude;
        Log.i("location", longitudetxt);
        latitude = location.getLatitude();
        String latitudetxt = "Latitude: " + latitude;
        Log.i("location", latitudetxt);
        double speed = location.getSpeed(); //spedd in meter/minute
        speed = (speed * 3600) / 1000;      // speed in km/minute

    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void update(final Context context) {
        this.contexte = context;
        Log.i("location", "Mise à jour !");
        LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Log.v("location",criteria.toString());
        String provider = service.getBestProvider(criteria, false);

        checkPermission();





    }
 public void checkPermission(){
     if (ActivityCompat.checkSelfPermission(contexte, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contexte, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

         Log.e("location", "Erreur de Permission, demande à l'utilistateur !");
         ActivityCompat.requestPermissions((Activity) contexte,
                 new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
         Log.e("location", " demandé à l'utilistateur !");

     }
 }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) { //PAS AU BON ENDROIT
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("location", "Permission reçue !");
                    LocationManager service = (LocationManager) contexte.getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    Log.v("location",criteria.toString());
                    String provider = service.getBestProvider(criteria, false);
                    try{ Location location = service.getLastKnownLocation(provider);
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                        MapsActivity mapact = (MapsActivity) contexte;
                        mapact.updatePosition(latitude,longitude);
                    }catch (SecurityException se){
                        Log.e("location","ERREUR PERMISSION");
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    checkPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}


