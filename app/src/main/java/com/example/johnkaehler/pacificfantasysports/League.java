package com.example.johnkaehler.pacificfantasysports;

import java.util.List;

/**
 * Created by John Kaehler on 4/17/2016.
 */
public class League {
    String name, password, commissionerEmail;
    List<String> memberEmails;
    boolean hasDrafted;

    public League(String _commissionerEmail, String _name, List<String> _memberEmails, String _password){
        name = _name;
        password = _password;
        commissionerEmail = _commissionerEmail;
        memberEmails = _memberEmails;
    }
    public League(String _commissionerEmail, String _name, List<String> _memberEmails, String _password, boolean _hasDrafted){
        name = _name;
        password = _password;
        commissionerEmail = _commissionerEmail;
        memberEmails = _memberEmails;
        hasDrafted = _hasDrafted;
    }
}
