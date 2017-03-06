package com.example.johnkaehler.pacificfantasysports;

import java.util.List;

/**
 * Created by John Kaehler on 5/6/2016.
 */
public class Team {
    int teamId;
    double totalPoints;
    String teamName;
    List<Athlete> athleteList;

    public Team(int _teamId, double _totalPoints, String _teamName, List<Athlete> _athleteList){
        teamId = _teamId;
        totalPoints = _totalPoints;
        teamName = _teamName;
        athleteList = _athleteList;
    }

    public Team(){

    }
}
