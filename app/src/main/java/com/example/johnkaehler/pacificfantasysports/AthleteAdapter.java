package com.example.johnkaehler.pacificfantasysports;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by John Kaehler on 2/16/2018.
 */

public class AthleteAdapter extends ArrayAdapter<Athlete> {

    public AthleteAdapter(Context context, ArrayList<Athlete> athletes){
        super(context,0,athletes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Athlete athlete = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.athlete_list_item, parent, false);
        }

        TextView tvAthleteName = (TextView)convertView.findViewById(R.id.tvAthleteName);
        TextView tvAthleteSport = (TextView) convertView.findViewById(R.id.tvAthleteSport);

        tvAthleteName.setText(athlete.getName());
        tvAthleteSport.setText(athlete.getSport());

        return convertView;

    }
}
