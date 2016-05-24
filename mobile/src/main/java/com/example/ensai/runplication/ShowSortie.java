package com.example.ensai.runplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowSortie extends AppCompatActivity {

    Double totalDistance=0.0;
    long totalRunTime=0;
    long totalPauseTime=0;
    Double speed=0.0;
    TextView distanceView=null;
    TextView runTimeView=null;
    TextView pauseTimeView=null;
    TextView speedView=null;
    Sortie sortie=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sortie);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle=extras.getBundle("sortie");
            totalDistance = bundle.getDouble("totalDistance");
            totalRunTime = bundle.getLong("totalRunTime");
            totalPauseTime = bundle.getLong("totalPauseTime");
        }

        runTimeView=(TextView) findViewById(R.id.totalTime);
        pauseTimeView=(TextView) findViewById(R.id.pauseTime);
        distanceView=(TextView) findViewById(R.id.totalDist);
        speedView=(TextView) findViewById(R.id.speed);

        if(totalRunTime>0){
            Log.i("ShowSortie","in totalRunTime>0");
            speed=totalDistance*3600/totalRunTime;
        }

        runTimeView.setText(totalRunTime+"");
        pauseTimeView.setText(totalPauseTime+"");
        distanceView.setText(totalDistance+"");
        speedView.setText(speed+getString(R.string.speedUnit_km_h));
    }
}
