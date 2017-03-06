package com.example.johnkaehler.pacificfantasysports;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewTeam extends AppCompatActivity {

    String commishEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        UserLocalStore userLocalStore = new UserLocalStore(this);
        String userEmail = userLocalStore.getLoggedInUser().email;

        Bundle b = getIntent().getExtras();
        commishEmail = "";
        if(b != null){
            commishEmail = b.getString("Commissioner Email");
        }

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.GetTeamListInBackground(commishEmail, userEmail, new GetCallback() {
            @Override
            public void done(Team _team) {
                displayTeam(_team);
            }

        });
    }

    private void displayTeam(Team _team) {

        int numAthletes = _team.athleteList.size();

        // Get the TableLayout
        TableLayout tl = (TableLayout) findViewById(R.id.maintable);

        // Go through each item in the array
        for (int i = 0; i < numAthletes; i++)
        {
            // Create a TableRow and give it an ID
            TableRow tr = new TableRow(this);
            tr.setId(100 + i);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // Create a TextView
            TextView athleteName = new TextView(this);
            athleteName.setPadding(10, 10, 10, 10);
            athleteName.setId(200 + i);
            athleteName.setText(_team.athleteList.get(i).name);
            athleteName.setTextColor(Color.WHITE);
            tr.addView(athleteName);

            // Create a TextView to house the name of the sport the athlete plays
            TextView tvSportName = new TextView(this);
            tvSportName.setPadding(10, 10, 10, 10);
            tvSportName.setId(i);
            tvSportName.setText(_team.athleteList.get(i).sport);
            tvSportName.setTextColor(Color.WHITE);
            tr.addView(tvSportName);

            tl.addView(tr);
        }
    }
}
