package com.example.ensai.runplication;

import android.content.Intent;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EcranAccueil extends AppCompatActivity implements View.OnClickListener  {
    Button boutonMap=null;
    Button boutonListeSortie=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_accueil);
        int i = 4;
        boutonMap = (Button) findViewById(R.id.mapButton);
        boutonMap.setOnClickListener(this);
        boutonListeSortie= (Button) findViewById(R.id.listeDesSortiesButton);
        boutonMap.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent1 =new Intent(this,MapsActivity.class);
        Intent intent2 =new Intent(this,ListeSorties.class);
        if (v==boutonListeSortie){
            startActivity(intent2);
        }
        if (v==boutonMap){
            startActivity(intent1);
        }
    }



}
