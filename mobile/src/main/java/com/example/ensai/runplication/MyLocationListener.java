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

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private Double longitude = (double) 0; //todo : mettre null
    private Double latitude = (double) 0; //todo : mettre null
    private Double speed2 = 0.0;
    private Context contexte;

    private Location location;

    @Override
    public void onLocationChanged(Location location) {

        this.location=location;
        longitude = location.getLongitude();
        String longitudetxt = "Longitude: " + longitude;
        Log.i("location", longitudetxt);
        latitude = location.getLatitude();
        String latitudetxt = "Latitude: " + latitude;
        Log.i("location", latitudetxt);
        double speed = location.getSpeed(); //speed in meter/minute
        speed2 = (speed * 3600) / 1000;      // speed in km/hour



    }

    public static int getMyPermissionsRequestAccessFineLocation() {
        return MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
    }

    public Location getLocation() { return location;}

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getSpeed() {
        return speed2;
    }

    public void update(final Context context) {
        this.contexte = context;
        Log.i("location", "Mise à jour !");
        LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Log.v("location",criteria.toString());

        checkPermission();

    }

    public void update (final Context context, MapsActivity mapAct){
        update(context);
        mapAct.updatePosition(this.latitude,this.longitude);
    }
 public void checkPermission(){
     if (ActivityCompat.checkSelfPermission(contexte, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contexte, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

         Log.e("location", "Erreur de Permission, demande à l'utilistateur !");
         ActivityCompat.requestPermissions((Activity) contexte,
                 new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
         Log.i("location", " demandé à l'utilistateur !");

     }else{
//
     }
 }


    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public void accessFineLocationPermissionGot() {
        Log.i("location", "Permission reçue !");
        LocationManager service = (LocationManager) contexte.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Log.v("location",criteria.toString());
        String provider = service.getBestProvider(criteria, false);
        Log.i("location","avant Maj");
        try{

            Location location = service.getLastKnownLocation(provider);//ECHEC;
            if (location==null) {
                Log.e("location", "Erreur : impossible d'obtenir la dernière position");
                long minTime = 1;
                float minDist = 1 ;
                service.requestLocationUpdates(LocationManager.GPS_PROVIDER ,minTime, minDist,this);
                location = service.getLastKnownLocation(provider);
                Log.i("location", "nouvelle tentative pour obtenir la dernière position");
            }else {
                this.longitude = location.getLongitude();

                this.latitude = location.getLatitude();
                Log.i("location", "apres Maj");
            }
        }catch (SecurityException se){
            Log.i("location","ERREUR PERMISSION");
        }
    }

}


