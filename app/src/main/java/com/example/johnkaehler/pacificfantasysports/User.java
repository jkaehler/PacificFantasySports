package com.example.johnkaehler.pacificfantasysports;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JFK on 5/14/2015.
 */
public class User {

    String firstName, lastName, email, password;
    int leagueID;

    //constructor
    public User(String _firstName, String _lastName, String _email, String _password){
        firstName = _firstName;
        lastName = _lastName;
        email = _email;
        password = _password;
    }
}