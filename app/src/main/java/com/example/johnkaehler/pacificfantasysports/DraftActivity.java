package com.example.johnkaehler.pacificfantasysports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DraftActivity extends AppCompatActivity {

    private int mLeagueId, mSchoolId;
    private ListView mTextViewBottom, mTextViewTop;
    private ArrayList<Athlete> mAthletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);

        Bundle b = getIntent().getExtras();
        if(b != null){
            mLeagueId = b.getInt("League Id");
            mSchoolId = b.getInt("School ID");
        }

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.retrieveAllTeamsInLeague(mLeagueId, new GetListOfTeamsCallback() {
            @Override
            public void done(ArrayList<Team> teams) {
                addListOfTeamsToListView(teams);
            }
        });

        serverRequests.retrieveAllAthletesAtSchool(mSchoolId, new GetListOfAthletesCallback() {
            @Override
            public void done(ArrayList<Athlete> athletes) {
                mAthletes = athletes;
                addListOfAthletesToListView(mAthletes);
                addAvailableSportsIcons(mAthletes);
            }
        });
    }

    private void addAvailableSportsIcons(ArrayList<Athlete> athletes){

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.sportsContainer);

        final ArrayList<String> listOfSportNames = new ArrayList<>();
        for(int i = 0; i < athletes.size(); i++){
            if(!listOfSportNames.contains(athletes.get(i).getSport())){
                listOfSportNames.add(athletes.get(i).getSport());
            }
        }
        for(int i = 0; i < listOfSportNames.size(); i++){

            final TextView textView = new TextView(this);
            textView.setText(listOfSportNames.get(i));
            textView.setId(i);
            textView.setPadding(10, 0, 10, 0);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = textView.getText().toString();
                    setAthletesList(s);
                }
            });
            linearLayout.addView(textView);
        }
    }

    private void setAthletesList(String sportName){
        ArrayList<String> athleteNames = new ArrayList<>();
        for(int i = 0; i < mAthletes.size(); i++){
            if(mAthletes.get(i).getSport().equals(sportName)){
                athleteNames.add(mAthletes.get(i).getName());
            }
        }
        mTextViewTop = (ListView)findViewById(R.id.topTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, athleteNames);
        mTextViewTop.setAdapter(arrayAdapter);
    }

    private void addListOfTeamsToListView(ArrayList<Team> teams) {
        ArrayList<String> teamNames = new ArrayList<>();
        for(int i = 0; i < teams.size(); i++){
            teamNames.add(teams.get(i).getTeamName());
        }
        mTextViewBottom = (ListView)findViewById(R.id.bottomTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamNames);
        mTextViewBottom.setAdapter(arrayAdapter);
    }

    private void addListOfAthletesToListView(ArrayList<Athlete> athletes){
        ArrayList<String> athleteNames = new ArrayList<>();
        for(int i = 0; i < athletes.size(); i++){
            athleteNames.add(athletes.get(i).getName());
        }
        mTextViewBottom = (ListView)findViewById(R.id.topTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, athleteNames);
        mTextViewBottom.setAdapter(arrayAdapter);
    }
}
