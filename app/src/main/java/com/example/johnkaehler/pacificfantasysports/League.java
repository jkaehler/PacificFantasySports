package com.example.johnkaehler.pacificfantasysports;

/**
 * Created by John Kaehler on 4/17/2016.
 */
public class League {
    String name, password;
    int leagueID;

    public League(String _name, String _password){
        name = _name;
        password = _password;
    }
    public League(String _name, String _password, int _id){
        name = _name;
        password = _password;
        leagueID = _id;
    }
}
