package com.example.johnkaehler.pacificfantasysports;

/**
 * Created by John Kaehler on 5/6/2016.
 */
public class Athlete {
    int athleteId;
    String name, sport, position;
    double points;

    public Athlete(int _athleteId, double _points, String _name, String _position, String _sport){
        athleteId = _athleteId;
        points = _points;
        name = _name;
        sport = _sport;
        position = _position;
    }
}
