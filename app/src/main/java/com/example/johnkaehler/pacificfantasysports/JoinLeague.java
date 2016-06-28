package com.example.johnkaehler.pacificfantasysports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JoinLeague extends ActionBarActivity implements View.OnClickListener{

    Button bJoinLeague;
    EditText etCommishEmail, etLeaguePassword;
    TextView tvGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_league);

        etCommishEmail = (EditText)findViewById(R.id.etCommissionerEmail);
        etLeaguePassword = (EditText)findViewById(R.id.etLeaguePassword);
        bJoinLeague = (Button)findViewById(R.id.bJoinLeague);
        tvGoBack = (TextView)findViewById(R.id.tvGoBack);

        bJoinLeague.setOnClickListener(this);
        tvGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bJoinLeague:
                if(allFieldsFilled()){
                    String commissionerEmail = etCommishEmail.getText().toString();
                    String leaguePassword = etLeaguePassword.getText().toString();
                    attemptToJoinLeague(commissionerEmail, leaguePassword);
                }
                else{
                    repromptLeagueInfo();
                }
                break;
            case R.id.tvGoBack:
                startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void repromptLeagueInfo() {
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(JoinLeague.this);//creates new AlertDialogue with context Login.this
        dialogueBuilder.setMessage("All fields required.");
        dialogueBuilder.setPositiveButton("Okay", null);
        dialogueBuilder.show();
    }

    private boolean allFieldsFilled() {
        if(etCommishEmail.getText().toString() == null || etLeaguePassword.getText().toString() == null)
            return false;
        return true;
    }

    private void attemptToJoinLeague(String emailParam, String leaguePasswordParam) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.attemptToJoinLeagueInBackground(emailParam, leaguePasswordParam, new JoinLeagueCallback() {
            @Override
            public void done(Boolean hasJoined) {
                if(hasJoined){
                }
            }
        });
    }
}
