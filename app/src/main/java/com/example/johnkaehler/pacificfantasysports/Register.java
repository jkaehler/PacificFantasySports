package com.example.johnkaehler.pacificfantasysports;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    TextView tvLoginLink;
    EditText etFirstName, etLastName, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bRegister = (Button) findViewById(R.id.bRegister);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(allFormsFilled()) {
                    User registeredData = new User(firstName, lastName, email, password);
                    registerUser(registeredData);
                    break;
                }
                else{
                    repromptRegisterInfo();
                    break;
                }

            case R.id.tvLoginLink:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    //if any of the fields are empty we will return false
    private boolean allFormsFilled() {
        if(etFirstName.getText().toString() == null || etLastName.getText().toString() == null || etPassword.getText().toString() == null || etEmail.getText().toString() == null){
            return false;
        }
        return true;
    }


    //creates an alert dialogue that tells user to fill out all fields.
    private void repromptRegisterInfo(){
        AlertDialog.Builder reprompt = new AlertDialog.Builder(Register.this);
        reprompt.setMessage("Please fill out all fields");
        reprompt.setPositiveButton("Okay", null);
        reprompt.show();
    }

    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}
