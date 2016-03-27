package com.example.johnkaehler.pacificfantasysports;

/**
 * Created by John Kaehler on 3/25/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context _context){
        userLocalDatabase = _context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User _user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", _user.firstName);
        spEditor.putString("lastName", _user.lastName);
        spEditor.putString("username", _user.email);
        spEditor.putString("password", _user.password);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String firstName = userLocalDatabase.getString("firstName", "");
        String lastName = userLocalDatabase.getString("lastName", "");
        String password = userLocalDatabase.getString("password", "");
        String email = userLocalDatabase.getString("username", "");

        User storedUser = new User(firstName, lastName, email, password);
        //testing for changes

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }

    public boolean getUserLoggedIn(){
        return(userLocalDatabase.getBoolean("loggedIn", false));
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}