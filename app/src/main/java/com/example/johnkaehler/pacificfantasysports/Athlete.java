package com.example.johnkaehler.pacificfantasysports;

/**
 * Created by John Kaehler on 5/6/2016.
 */
public class Athlete {
    int athleteId, points, jerseyNumber;
    String name, sport;

    public Athlete(int _athleteId, int _points, int _jerseyNumber, String _name, String _sport){
        athleteId = _athleteId;
        points = _points;
        jerseyNumber = _jerseyNumber;
        name = _name;
        sport = _sport;
    }

}
