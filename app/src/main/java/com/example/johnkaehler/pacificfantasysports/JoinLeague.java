package com.example.johnkaehler.pacificfantasysports;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinLeague extends AppCompatActivity implements View.OnClickListener{

    Button bJoinLeague;
    EditText etCommishEmail, etLeaguePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_league);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bJoinLeague:

        }
    }
}
