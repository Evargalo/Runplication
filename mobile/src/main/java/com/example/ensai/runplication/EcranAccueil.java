package com.example.ensai.runplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EcranAccueil extends AppCompatActivity implements View.OnClickListener  {
    Button boutonMap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_accueil);
        int i = 4;
        boutonMap = (Button) findViewById(R.id.mapButton);
        boutonMap.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

}
