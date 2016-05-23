package com.example.ensai.runplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class OnRunActivity extends AppCompatActivity implements View.OnClickListener {

    Sortie sortie = null;
    Date date = null;
    MyLocationListener locationListener= null;
    long startTime = 0;
    Button boutonMap=null;
    Button boutonPause=null;
    Button boutonStop=null;
    boolean onPause=false;
    long totalTime=0;
    long pauseTime=0;
    Location lastLocation=null;
    Location newLocation=null;
    Double lastLongitude=0.0;
    Double lastLatitude=0.0;
    Double newLongitude=0.0;
    Double newLatitude=0.0;
    Double totalDistance=0.0;
    long totalRunTime=0;
    long totalPauseTime=0;
    TextView distanceView=null;
    TextView runTimeView=null;
    TextView pauseTimeView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_run);

        locationListener=new MyLocationListener();

        Log.i("onRun","locationlistener créé");
        sortie= new Sortie(this);
        date = Calendar.getInstance().getTime();
        startTime=Calendar.getInstance().getTimeInMillis();

        runTimeView=(TextView) findViewById(R.id.totalTime);
        pauseTimeView=(TextView) findViewById(R.id.pauseTime);
        distanceView=(TextView) findViewById(R.id.totalDist);

        boutonMap = (Button) findViewById(R.id.mapButton);
        boutonPause = (Button) findViewById(R.id.pauseButton);
        boutonStop = (Button) findViewById(R.id.stopButton);

        boutonMap.setOnClickListener(this);
        boutonPause.setOnClickListener(this);
        boutonStop.setOnClickListener(this);



        totalRunTime=0;
        totalPauseTime=0;
        totalDistance=0.0;

        Log.i("onRun","layout terminé");
        newLocation=locationListener.getLocation();

        lastLocation=newLocation;
        newLongitude=locationListener.getLongitude();
        lastLongitude=locationListener.getLongitude();
        newLatitude=locationListener.getLatitude();
        lastLatitude=locationListener.getLatitude();

        Log.i("onRun","locationlistener consulté" + newLatitude);
        final Handler h = new Handler();
        final int delay = 1000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                updateLocation();
                h.postDelayed(this, delay);

                runTimeView.setText(totalRunTime+"");
                pauseTimeView.setText(totalPauseTime+"");
                distanceView.setText(totalDistance+"");
            }
        }, delay);
    }


    private void updateLocation(){
        Log.i("onRun","updateLocation lancé");
        newLocation=locationListener.getLocation();
        if (onPause){totalPauseTime++;}
        else{totalRunTime++;

            Float distanceRan=lastLocation.distanceTo(newLocation);
            totalDistance=totalDistance+distanceRan;

            newLongitude=locationListener.getLongitude();
            newLatitude=locationListener.getLatitude();
            lastLongitude=newLongitude;
            lastLatitude=newLatitude;
        }
        Log.i("onRun","updateLocation terminé");
    lastLocation=newLocation;
    }

    public void onClick(View v) {
        Intent intent1 =new Intent(this,MapsActivity.class);
        Intent intent2 =new Intent(this,ShowSortie.class);
        if (v==boutonMap){
            startActivity(intent1);
        }
        if (v==boutonStop){
            startActivity(intent2);
        }
        if (v==boutonPause){
            if (onPause){
                boutonPause.setText("R.string.pauseButton");
                boutonPause.setBackgroundColor(getResources().getColor(R.color.orange));
                Toast.makeText(this,R.string.endOfPauseMessage,Toast.LENGTH_SHORT).show();
                onPause=false;
            }
            if (!onPause){
                boutonPause.setText("R.string.resumeRunButton");
                boutonPause.setBackgroundColor(getResources().getColor(R.color.green));
                Toast.makeText(this,R.string.startOfPauseMessage,Toast.LENGTH_SHORT).show();
                onPause=true;
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MyLocationListener.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationListener.accessFineLocationPermissionGot();
                    this.lastLocation =(locationListener.getLocation());
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    locationListener.checkPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
