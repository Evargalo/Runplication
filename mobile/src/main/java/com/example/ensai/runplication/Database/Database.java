package com.example.ensai.runplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ensai on 03/05/16.
 */
public class Database extends SQLiteOpenHelper {
    private static int DATABASE_VERSION=1;
    public Database(Context context){
        super(context,"BaseOfTasks",null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sortie (id INT PRIMARY KEY, date DATE, type TEXT, " +
                "distance NUMERIC, duree NUMERIC, duree_pause NUMERIC, " +
                "vitesse NUMERIC, allure NUMERIC, calories NUMERIC, commment TEXT); " +
                "CREATE TABLE releve (id TEXT PRIMARY KEY, idSortie INT, date DATE, " +
                "longitude NUMERIC, latitude NUMERIC), " +
                "FOREIGN KEY (idSortie) REFERENCES sortie(id);" );
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }

}
