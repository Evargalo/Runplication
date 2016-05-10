package com.example.ensai.runplication;

import android.Manifest;
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

    private Double longitude = (double) 0; //todo : mettre null
    private Double latitude = (double) 0; //todo : mettre null

    @Override
    public void onLocationChanged(Location location) {

        longitude = location.getLongitude();
        String longitudetxt = "Longitude: " + longitude;
        Log.v("longitude", longitudetxt);
        latitude = location.getLatitude();
        String latitudetxt = "Latitude: " + latitude;
        Log.v("latitude", latitudetxt);
        double speed = location.getSpeed(); //spedd in meter/minute
        speed = (speed * 3600) / 1000;      // speed in km/minute

    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void update(Context context) {
        LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = service.getLastKnownLocation(provider);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}


