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

public class CreateLeague extends ActionBarActivity implements View.OnClickListener{

    Button bCreateLeague;
    EditText etLeagueName, etLeaguePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        etLeagueName = (EditText)findViewById(R.id.etLeagueName);
        etLeaguePassword = (EditText)findViewById(R.id.etLeaguePassword);
        bCreateLeague = (Button)findViewById(R.id.bCreateLeague);

        bCreateLeague.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bCreateLeague:
                if(AllFormsFilled()){
                    String leagueName = etLeagueName.getText().toString();
                    String leaguePassword = etLeaguePassword.getText().toString();
                    League l = new League(leagueName, leaguePassword);
                    createLeague(l);
                }
                else repromptRegisterInfo();

        }
    }

    private boolean AllFormsFilled(){
        if(etLeagueName == null || etLeaguePassword == null) return false;
        return true;
    }

    private void createLeague(League _l){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.createLeagueInBackground(_l);

    }

    private void repromptRegisterInfo(){
        AlertDialog.Builder reprompt = new AlertDialog.Builder(CreateLeague.this);
        reprompt.setMessage("Please fill out all fields");
        reprompt.setPositiveButton("Okay", null);
        reprompt.show();
    }
}
