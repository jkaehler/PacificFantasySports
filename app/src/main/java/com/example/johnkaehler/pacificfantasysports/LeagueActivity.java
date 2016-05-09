package com.example.johnkaehler.pacificfantasysports;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.List;

public class LeagueActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        populateListView();

    }

    private void populateListView() {
        //create list of items
        String[] myItems = {"balls", "penis", "alasaurus"};
        //build adapter
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.team, myItems);

        //configure the list
        TableLayout table = (TableLayout)findViewById(R.id.tableLayout);
        //table.set
        //table.setAdapter(adapter);
    }
}
