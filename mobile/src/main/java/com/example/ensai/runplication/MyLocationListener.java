package com.example.ensai.runplication;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ensai on 10/05/16.
 */
public class MyLocationListener implements LocationListener {

    private Double longitude = (double) 0; //todo : mettre null
    private Double latitude = (double) 0; //todo : mettre null

    @Override
    public void onLocationChanged(Location loc) {
        Context context=null;
       // pb.setVisibility(View.INVISIBLE);
        Toast.makeText(
                context,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.getLongitude(), Toast.LENGTH_SHORT).show();
       longitude = loc.getLongitude();
        String longitudetxt = "Longitude: " + longitude;
        Log.v("longitude", longitudetxt);
        latitude = loc.getLatitude();
        String latitudetxt = "Latitude: " + latitude;
        Log.v("latitude", latitudetxt);

    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}


