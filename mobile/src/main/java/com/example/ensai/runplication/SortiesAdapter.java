package com.example.ensai.runplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ensai on 10/05/16.
 */
public class SortiesAdapter extends BaseAdapter {

    Context context;
    List<Sortie> sorties = new ArrayList<Sortie>();

    public SortiesAdapter(Context context, List<Sortie> sorties){
        this.context=context;
        this.sorties=sorties;

    }

    public int getCount() {
        return sorties.size();
    }

    @Override
    public Object getItem(int position) {
        return sorties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sortie sortie=(Sortie)getItem(position);
        View view = LayoutInflater.from(context).inflate(R.layout.sortie_in_liste,parent,false);
        TextView distance = (TextView) view.findViewById(R.id.distance);
        distance.setText(sortie.getDistance().toString() + R.string.unite_distance);
        TextView commentaires = (TextView) view.findViewById(R.id.commentaires);
        commentaires.setText(sortie.getCommentaire());

        return null;
    }
}
