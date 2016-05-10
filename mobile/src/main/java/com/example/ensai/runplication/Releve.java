package com.example.ensai.runplication;

import android.location.Location;

import java.util.Calendar;

/**
 * Created by ensai on 10/05/16.
 */
public class Releve {
    Calendar time;
    Location place;
    Sortie sortie;

    public Releve() {
    }

    public void Save(){

    }



    // Getters and Setters


    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Location getPlace() {
        return place;
    }

    public void setPlace(Location place) {
        this.place = place;
    }
}
