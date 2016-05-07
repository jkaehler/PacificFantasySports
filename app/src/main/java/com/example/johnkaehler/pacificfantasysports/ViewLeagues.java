package com.example.johnkaehler.pacificfantasysports;

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

public class ViewLeagues extends AppCompatActivity {

    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leagues);

        userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUser();

        //dynamicallyAddLeagueButtons(user.email);
    }

    private void dynamicallyAddLeagueButtons(String email) {

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.getLeagueDataInBackground(email, new GetLeagueCallback() {
            @Override
            public void done(League league) {
                if(league == null){//if no one is returned
                    //showErrorMessage();//show error message
                    //not in any leagues
                }
                else{
                    //put information in here

                }
            }
        });
        //dynamically add buttons to view
        final LinearLayout lm = (LinearLayout) findViewById(R.id.InnerLayout);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT);

        //Create four
        for(int j=0;j<=4;j++)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create Button
            final Button btn = new Button(this);
            //btn.setWidth(200);
            // Give button an ID
            btn.setId(j+1);
            btn.setText("League "+j+1);
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            final int index = j;
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

}
