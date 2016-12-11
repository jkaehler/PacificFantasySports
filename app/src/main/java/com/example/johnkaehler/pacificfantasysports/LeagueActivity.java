package com.example.johnkaehler.pacificfantasysports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeagueActivity extends AppCompatActivity implements View.OnClickListener{

    String commishEmail, userEmail;
    boolean leagueHasDrafted, userIsCommissioner;
    TextView tvGoBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        tvGoBack2 = (TextView) findViewById(R.id.tvGoBack);
        tvGoBack2.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        commishEmail = "";
        if(b != null){
            commishEmail = b.getString("Commissioner Email");
        }

        UserLocalStore userLocalStore = new UserLocalStore(this);
        userEmail = userLocalStore.getLoggedInUser().email;

        leagueHasDrafted = true;
        userIsCommissioner = (userEmail.equals(commishEmail));

        //setViewUndraftedCommissioner();

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.OuterLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = Gravity.CENTER;

        createButtons(linearLayout, leagueHasDrafted, userIsCommissioner);


    }

    private void createButtons(LinearLayout _linearLayout, boolean _leagueHasDrafted, boolean _userIsCommissioner) {
        int numButtons;
        String[] buttonTexts;
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = Gravity.CENTER;

        if(_userIsCommissioner){
            if(_leagueHasDrafted){
                numButtons = 5;//WE'VE DRAFTED SO WE NEED ALL THE BUTTONS PLUS ONE MORE FOR COMMISH TO ADMIN LEAGUE
                buttonTexts = new String[]{"Manage League", "View My Teams", "View Standings", "Show All Teams", "View Matchups"};
            } else {
                numButtons = 1; //WE HAVEN'T DRAFTED SO JUST PUT ONE BUTTON FOR THE COMMISSIONER TO ADMIN THE LEAGUE HERE
                buttonTexts = new String[]{"Manage League"};
            }
        }
        else {
            numButtons = 4;////NORMAL ASS PERSON ONLY GETS TO SEE THE NORMAL FOUR BUTTONS
            buttonTexts = new String[]{"View My Teams", "View Standings", "Show All Teams", "View Matchups"};
        }

        final Button[] buttonArray = new Button[numButtons];

        for(int i = 0; i < numButtons; i++){
            buttonArray[i] = new Button(this);
            buttonArray[i].setText(buttonTexts[i]);
            buttonArray[i].setId(i+1);
            buttonArray[i].setLayoutParams(buttonParams);
            _linearLayout.addView(buttonArray[i]);
            buttonArray[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        String btnText = b.getText().toString();
        switch (btnText) {
            case "View My Teams":
                Intent i = new Intent(getBaseContext(), ViewTeam.class);
                i.putExtra("Commissioner Email", commishEmail);
                startActivity(i);
                break;
            case "View Standings":
                Intent i2 = new Intent(getBaseContext(), ViewStandings.class);
                i2.putExtra("Commissioner Email", commishEmail);
                startActivity(i2);
                break;
            case "Show All Teams":
                Intent i3 = new Intent(getBaseContext(), ViewShowAllTeams.class);
                i3.putExtra("Commissioner Email", commishEmail);
                startActivity(i3);
                break;
            case "View Matchups":
                Intent i4 = new Intent(getBaseContext(), ViewShowMatchups.class);
                i4.putExtra("Commissioner Email", commishEmail);
                startActivity(i4);
                break;
            case "Manage League":
                Intent i5 = new Intent(getBaseContext(), ViewManageLeague.class);
                i5.putExtra("Commissioner Email", commishEmail);
                startActivity(i5);
                break;
        }
    }
}