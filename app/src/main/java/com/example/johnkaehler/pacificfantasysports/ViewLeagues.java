package com.example.johnkaehler.pacificfantasysports;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class ViewLeagues extends AppCompatActivity {

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leagues);

        //userLocalStore = new UserLocalStore(this);

        //User user = userLocalStore.getLoggedInUser();

        //ServerRequests serverRequests = new ServerRequests(this);
        //serverRequests.getLeagueDataInBackground(user);

        for(int i = 0; i < 5; i++){
            Button myButton = new Button(this);
            myButton.setText("Push me. You won't.");

            /*LinearLayout ll = (LinearLayout)findViewById(R.id.);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);*/
        }

    }

}
