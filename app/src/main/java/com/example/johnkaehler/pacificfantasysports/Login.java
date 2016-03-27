package com.example.johnkaehler.pacificfantasysports;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button bLogin;//declares button widget
    EditText etEmail, etPassword;//editable text
    TextView tvRegisterLink;//uneditable text

    UserLocalStore userLocalStore;//declares UserLocalStore class instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {//savedInstanceState is the state of the program. Bundles enable data transfer b/w activities
        super.onCreate(savedInstanceState);//gives savedInstanceState to superclass
        setContentView(R.layout.activity_login);//sets activity content from resource layout - activity_login.

        etEmail = (EditText) findViewById(R.id.etEmail);//returns view specified by etUsername casts to type EditText
        etPassword = (EditText) findViewById(R.id.etPassword);//returns view specified by etPassword, casts to type EditText
        bLogin = (Button) findViewById(R.id.bLogin);//returns button specified by id bLogin, casts to type Button, assigns name bLogin
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);//Use this instead of string because it has setOnClickListener method

        bLogin.setOnClickListener(this);//establishes onclicklistener with event as parameter.
        tvRegisterLink.setOnClickListener(this);//same as above

        userLocalStore = new UserLocalStore(this);//instantiates new userLocalStore with context 'this'
    }

    @Override
    public void onClick(View v) {//method that takes the view that was clicked as the argument
        switch (v.getId()){//switch statement that gives us the id of the view sent in
            case R.id.bLogin://case where login button is clicked
                String email = etEmail.getText().toString();//creates string 'username' that has a value of the text entered into username field
                String password = etPassword.getText().toString();//creates string 'password' that has a value of the text entered into password field

                User user = new User(null, null, email, password);//instantiates new user with given arguments
                authenticate(user);//determines whether or not user input is valid
                break;

            case R.id.tvRegisterLink://case 'register' is clicked
                startActivity(new Intent(this, Register.class));//start new activity 'register'
        }
    }
    //some comment++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void authenticate(User user){//method to verify user
        ServerRequests serverRequests = new ServerRequests(this);//creates new ServerRequest with context 'this'
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser == null){//if no one is returned
                    showErrorMessage();//show error message
                }
                else{
                    logUserIn(returnedUser);//otherwise we log the returnedUser in
                }
            }
        });
    }

    private void showErrorMessage(){
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(Login.this);//creates new AlertDialogue with context Login.this
        dialogueBuilder.setMessage("Incorrect User Details");
        dialogueBuilder.setPositiveButton("Okay", null);
        dialogueBuilder.show();
    }

    private void logUserIn(User returnedUser){
        userLocalStore.setUserLoggedIn(true);
        userLocalStore.storeUserData(returnedUser);
        startActivity(new Intent(this, MainActivity.class));
    }
}
