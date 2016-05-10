package com.example.ensai.runplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ensai.runplication.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class ListeSorties extends AppCompatActivity implements View.OnClickListener {


    Button button = null;

    public void onClick(View v){
        Toast.makeText(this,R.string.textOnClick,Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(this, Sortie.class);
        // myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_sorties);


        List<Sortie> sortieList =new ArrayList<Sortie>();
        Database myDB=new Database(this);
        String[] arguments={};
        // myDB.getReadableDatabase().rawQuery("SELECT * FROM task;", arguments);


        Cursor c = myDB.getReadableDatabase().rawQuery("SELECT * FROM sortie", arguments);
        Sortie sortie=null;
        int nbrows=c.getCount();
        while(c.moveToNext())
        {
            sortie=new Sortie(this);
            sortie.setId(c.getInt(0));
            sortie.setDistance(c.getDouble(4));
            sortie.setDuree(c.getDouble(5));
            sortie.setDuree_pause(c.getDouble(6));
            sortie.setCommentaire(c.getString(10));
            sortieList.add(sortie);
        }
        c.close();

        //ArrayAdapter<Sortie> sortieTable =new ArrayAdapter<Sortie>(this,android.R.layout.simple_list_item_1,android.R.id.text1,sortieList);
        SortiesAdapter sortiesAdapter= new SortiesAdapter(this, sortieList);

        ListView listOfSorties=(ListView) findViewById(R.id.listeDesSorties);
        listOfSorties.setAdapter(sortiesAdapter);
    }

}

