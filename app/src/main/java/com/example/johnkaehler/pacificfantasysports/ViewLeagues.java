package com.example.johnkaehler.pacificfantasysports;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
            public void done(List<String> listOfLeagues) {
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

    private void dynamicallyAddLeagueButtons(List<String> listOfLeagues) {

        int listSize = listOfLeagues.size();

        //dynamically add buttons to view
        final LinearLayout lm = (LinearLayout) findViewById(R.id.InnerLayout);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT);

        //Create four
        for(int j=0;j<listSize;j++)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create Button
            final Button btn = new Button(this);
            btn.setWidth(200);
            // Give button an ID
            btn.setId(j+1);
            btn.setText(listOfLeagues.get(j));
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            // Set click listener for button
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                }
            });

            //Add button to LinearLayout
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }
    }

    private void createButtonObjects() {
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
