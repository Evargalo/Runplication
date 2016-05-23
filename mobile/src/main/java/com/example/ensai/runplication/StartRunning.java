package com.example.ensai.runplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartRunning extends AppCompatActivity implements View.OnClickListener {

    Button boutonStartActivity=null;
    Button boutonLoadPlan=null;
    Button boutonParametres=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_running);

        boutonStartActivity= (Button) findViewById(R.id.beginActivityButton);
        boutonStartActivity.setOnClickListener(this);

        boutonParametres= (Button) findViewById(R.id.ParametresButton);
        boutonParametres.setOnClickListener(this);

        boutonLoadPlan= (Button) findViewById(R.id.loadPlanButton);
        boutonLoadPlan.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent1 =new Intent(this,OnRunActivity.class);
        Intent intent2 =new Intent(this,LoadPlanActivity.class);
        Intent intent3 =new Intent(this,Parametres.class);
        if (v==boutonStartActivity){
            startActivity(intent1);
        }
        if (v==boutonLoadPlan){
            startActivity(intent2);
        }
        if (v==boutonParametres){
            startActivity(intent3);
        }
    }



}
