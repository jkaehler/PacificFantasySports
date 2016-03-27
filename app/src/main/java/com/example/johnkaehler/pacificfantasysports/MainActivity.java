package com.example.johnkaehler.pacificfantasysports;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button bLogout, bCreateLeague, bViewLeagues;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCreateLeague = (Button)findViewById(R.id.bCreateLeagues);
        bCreateLeague.setOnClickListener(this);

        bViewLeagues = (Button)findViewById(R.id.bViewLeagues);
        bViewLeagues.setOnClickListener(this);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(authenticate()){
            displayUserDetails();
        }
        else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
            case R.id.bCreateLeagues:
                startActivity(new Intent(this, CreateLeague.class));
        }
    }
}
