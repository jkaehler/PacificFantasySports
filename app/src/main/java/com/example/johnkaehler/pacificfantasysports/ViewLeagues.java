package com.example.johnkaehler.pacificfantasysports;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewLeagues extends AppCompatActivity implements View.OnClickListener{

    UserLocalStore userLocalStore;
    TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leagues);

        userLocalStore = new UserLocalStore(this);
        String email = userLocalStore.getLoggedInUser().email;

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.getLeagueDataInBackground(email, new GetListOfLeaguesCallback() {
            @Override
            public void done(List<LeagueEmailNameJoin> listOfLeagues) {
                if (listOfLeagues == null) {
                    //Show error message
                } else {
                    dynamicallyAddLeagueButtons(listOfLeagues);
                }
            }
        });

        goBack = (TextView)findViewById(R.id.tvGoBack);
        goBack.setOnClickListener(this);
    }

    private void dynamicallyAddLeagueButtons(final List<LeagueEmailNameJoin> listOfLeagues) {

        int listSize = listOfLeagues.size();

        final LinearLayout lm = (LinearLayout) findViewById(R.id.InnerLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int j=0;j<listSize;j++)
        {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);

            final Button btn = new Button(this);
            final String leagueName = listOfLeagues.get(j).getLeagueName();
            final String commishEmail = listOfLeagues.get(j).getCommishEmail();
            btn.setId(j + 1);
            btn.setText(leagueName);
            btn.setLayoutParams(params);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(getBaseContext(), LeagueActivity.class);
                    i.putExtra("Commissioner Email", commishEmail);
                    startActivity(i);
                }
            });

            ll.addView(btn);
            lm.addView(ll);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvGoBack:
                startActivity(new Intent(this, MainActivity.class));
        }
    }
}
