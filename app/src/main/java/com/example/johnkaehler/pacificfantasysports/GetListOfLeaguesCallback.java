package com.example.johnkaehler.pacificfantasysports;

import java.util.List;

/**
 * Created by John Kaehler on 6/27/2016.
 */
public interface GetListOfLeaguesCallback {
    public abstract void done(List<LeagueEmailNameJoin> listOfLeagues);
}
