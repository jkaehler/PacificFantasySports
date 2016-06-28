package com.example.johnkaehler.pacificfantasysports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateLeague extends ActionBarActivity implements View.OnClickListener{

    Button bCreateLeague;
    EditText etLeagueName, etLeaguePassword;
    UserLocalStore userLocalStore;
    TextView tvGoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        userLocalStore=new UserLocalStore(this);

        //create variables for all xml objects
        etLeagueName = (EditText)findViewById(R.id.etLeagueName);
        etLeaguePassword = (EditText)findViewById(R.id.etLeaguePassword);
        bCreateLeague = (Button)findViewById(R.id.bCreateLeague);
        tvGoBack = (TextView)findViewById(R.id.tvGoBack);

        //set OnClickListeners
        bCreateLeague.setOnClickListener(this);
        tvGoBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bCreateLeague:
                if(AllFormsFilled()){
                    String leagueName = etLeagueName.getText().toString();
                    String leaguePassword = etLeaguePassword.getText().toString();
                    User user = userLocalStore.getLoggedInUser();
                    League l = new League(user.email, leagueName, null, leaguePassword);
                    createLeague(l);
                }
                else repromptRegisterInfo();
            case R.id.tvGoBack:
                startActivity(new Intent(this, MainActivity.class));
        }
    }

    private boolean AllFormsFilled(){
        if(etLeagueName == null || etLeaguePassword == null) return false;
        return true;
    }

    private void createLeague(League _l){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.createLeagueInBackground(_l, new CreateLeagueCallback() {
            @Override
            public void done() {

            }
        });



        startActivity(new Intent(this, MainActivity.class));
    }

    private void repromptRegisterInfo(){
        AlertDialog.Builder reprompt = new AlertDialog.Builder(CreateLeague.this);
        reprompt.setMessage("Please fill out all fields");
        reprompt.setPositiveButton("Okay", null);
        reprompt.show();
    }
}
