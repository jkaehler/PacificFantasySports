package com.example.johnkaehler.pacificfantasysports;

/**
 * Created by John Kaehler on 7/12/2016.
 */
public class LeagueEmailNameJoin {
    private String commishEmail, leagueName;
    public LeagueEmailNameJoin(String _commishEmail, String _leagueName){
        commishEmail = _commishEmail;
        leagueName = _leagueName;
    }

    public void setCommishEmail(String _commishEmail){
        commishEmail = _commishEmail;
    }

    public void setLeagueName(String _leagueName){
        leagueName = _leagueName;
    }

    public String getCommishEmail(){
        return commishEmail;
    }

    public String getLeagueName(){
        return leagueName;
    }
}
