package com.example.ensai.runplication;

import android.content.Context;

import com.example.ensai.runplication.Database.Database;
import com.example.ensai.runplication.Releve;
import com.example.ensai.runplication.SortieType;

import java.util.Calendar;

/**
 * Created by ensai on 10/05/16.
 */
public class Sortie {

    public Context context;
    private Integer id;
    private Double distance;
    private Double duree;
    private Double duree_pause;
    private Double pos_denivele;
    private Double neg_denivele;
    private Calendar heure_debut;
    private Double calories;
    private SortieType type;
    private Releve releve;
    private String commentaire;

    public Sortie(Context context) {
        this.context=context;
    }

    public void start(){}
    public void finish(){}
    public void register(){
        Database myDB=new Database(context);
        String[] arguments={"",""};
        arguments[0]=id.toString();
        arguments[1]=distance.toString();
        myDB.getWritableDatabase().rawQuery("INSERT INTO sortie VALUES (?,?);",arguments);


    }
    public void show(){}

    public String toString(){
        return (this.heure_debut+" ; "+this.type+" ; "+this.distance);
    }

    // Getters et Setters

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Releve getReleve() {
        return releve;
    }

    public void setReleve(Releve releve) {
        this.releve = releve;
    }

    public SortieType getType() {
        return type;
    }

    public void setType(SortieType type) {
        this.type = type;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Calendar getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Calendar heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Double getNeg_denivele() {
        return neg_denivele;
    }

    public void setNeg_denivele(Double neg_denivele) {
        this.neg_denivele = neg_denivele;
    }

    public Double getPos_denivele() {
        return pos_denivele;
    }

    public void setPos_denivele(Double pos_denivele) {
        this.pos_denivele = pos_denivele;
    }

    public Double getDuree_pause() {
        return duree_pause;
    }

    public void setDuree_pause(Double duree_pause) {
        this.duree_pause = duree_pause;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
