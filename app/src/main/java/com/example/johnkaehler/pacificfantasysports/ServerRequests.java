package com.example.johnkaehler.pacificfantasysports;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ServerRequests extends Activity{

    ProgressDialog progressDialog;

    public static final int CONNECTION_TIMEOUT = 1000 * 15;//how long it will take for connection to timeout
    public static final String SERVER_ADDRESS = "http://10.0.2.2/PFS_PHP/";//sending data to local hosted server

    public ServerRequests(Context context){//constructor
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback){
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void createLeagueInBackground(League _l, CreateLeagueCallback createLeagueCallback){
        progressDialog.show();
        new CreateLeagueAsyncTask(_l, createLeagueCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callBack){
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();
    }

    public void getLeagueDataInBackground(String email, GetListOfLeaguesCallback getListOfLeaguesCallback) {
        progressDialog.show();
        new RetrieveLeagueAsyncTask(email, getListOfLeaguesCallback).execute();
    }

    public void attemptToJoinLeagueInBackground(String commishEmail, String leaguePw, String memberEmail, JoinLeagueCallback joinLeagueCallback) {
        progressDialog.show();
        new JoinLeagueAsyncTask(commishEmail, leaguePw, memberEmail, joinLeagueCallback).execute();
    }

    public void GetTeamListInBackground(String commishEmail, String userEmail, GetCallback getCallback) {
        progressDialog.show();
        new GetTeamListAsyncTask(commishEmail, userEmail, getCallback).execute();
    }

    public class JoinLeagueAsyncTask extends AsyncTask<Void, Void, String>{

        String leagueEmail, leaguePassword, memberEmail;
        JoinLeagueCallback joinLeagueCallback;

        public JoinLeagueAsyncTask(String _commishEmail, String _leaguePw, String _memberEmail, JoinLeagueCallback _joinLeagueCallback){
            leagueEmail = _commishEmail;
            leaguePassword = _leaguePw;
            joinLeagueCallback = _joinLeagueCallback;
            memberEmail = _memberEmail;
        }

        @Override
        protected String doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", leagueEmail));
            dataToSend.add(new BasicNameValuePair("password", leaguePassword));
            dataToSend.add(new BasicNameValuePair("member_email", memberEmail));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"joinleague.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                progressDialog.dismiss();
                return result;

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String _msg) {
            progressDialog.dismiss();
            joinLeagueCallback.done(_msg);
            super.onPostExecute(_msg);
        }
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback){
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("firstName", user.firstName));
            dataToSend.add(new BasicNameValuePair("lastName", user.lastName));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"register.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class CreateLeagueAsyncTask extends AsyncTask<Void, Void, Void> {
        League l;
        CreateLeagueCallback createLeagueCallback;

        public CreateLeagueAsyncTask(League _league, CreateLeagueCallback _createLeagueCallback){
            this.l = _league;
            createLeagueCallback = _createLeagueCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("commissionerEmail", l.commissionerEmail));
            dataToSend.add(new BasicNameValuePair("leagueName", l.name));
            dataToSend.add(new BasicNameValuePair("leaguePassword", l.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"createleague.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"FetchUserData.php");

            User returnedUser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);
                if(jObject.length() == 0){
                    returnedUser = null;
                }
                else{
                    String firstName = jObject.getString("first_name");
                    String lastName = jObject.getString("last_name");
                    returnedUser = new User(firstName, lastName, user.email, user.password);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }

    public class RetrieveLeagueAsyncTask extends AsyncTask <Void, Void, List<LeagueEmailNameJoin>> {

        String email;
        GetListOfLeaguesCallback leagueCallback;

        public RetrieveLeagueAsyncTask(String _email, GetListOfLeaguesCallback _leagueCallback){
            this.email = _email;
            this.leagueCallback = _leagueCallback;
        }

        @Override
        protected List<LeagueEmailNameJoin> doInBackground(Void... params) {
            ArrayList<NameValuePair> data = new ArrayList<>();
            data.add(new BasicNameValuePair("Email", email));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"viewleague.php");

            List<LeagueEmailNameJoin> returnedLeagues = new ArrayList<>();

            try{
                post.setEntity(new UrlEncodedFormEntity(data));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() == 0){
                    returnedLeagues = null;
                }
                else{
                Iterator<String> keys = jsonObject.keys();
                    while(keys.hasNext()){
                        String leagueName = keys.next();
                        String commishEmail = jsonObject.getString(leagueName);
                        LeagueEmailNameJoin leagueEmailNameJoin = new LeagueEmailNameJoin(commishEmail, leagueName);
                        returnedLeagues.add(leagueEmailNameJoin);
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }

            return returnedLeagues;
        }
        @Override
        protected void onPostExecute(List<LeagueEmailNameJoin> listOfLeagues) {

            progressDialog.dismiss();
            leagueCallback.done(listOfLeagues);
            super.onPostExecute(listOfLeagues);
        }
    }

    private class GetTeamListAsyncTask extends AsyncTask<Void, Void, Team>
    {

        String commishEmail, userEmail;
        GetCallback getCallback;

        public GetTeamListAsyncTask(String _commishEmail, String _userEmail, GetCallback _getCallback) {
            commishEmail = _commishEmail;
            userEmail = _userEmail;
            getCallback = _getCallback;
        }

        @Override
        protected Team doInBackground(Void... params) {

            ArrayList<NameValuePair> data = new ArrayList<>();
            data.add(new BasicNameValuePair("commishEmail", commishEmail));
            data.add(new BasicNameValuePair("userEmail", userEmail));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS +"returnTeam.php");

            Team returnedTeam = new Team();
            List<Athlete> athletes = new ArrayList<Athlete>();

            try{
                post.setEntity(new UrlEncodedFormEntity(data));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONArray jsonArray = new JSONArray(result);

                if(jsonArray.length() == 0){
                    returnedTeam = null;
                }
                else{
                    int numOfAthletes = jsonArray.length();
                    double totalPoints = 0;

                    for(int i = 0; i < numOfAthletes; i++){
                        String index = jsonArray.getString(i);
                        JSONObject jsonObject = new JSONObject(index);
                        String name = jsonObject.getString("name");
                        String sport = jsonObject.getString("sport");
                        String position = jsonObject.getString("position");
                        int athleteID = jsonObject.getInt("athlete_id");
                        double points = jsonObject.getDouble("points");
                        totalPoints += points;
                        Athlete athlete = new Athlete(athleteID, points, name, position, sport);
                        athletes.add(athlete);
                    }
                    returnedTeam.athleteList = athletes;
                    returnedTeam.teamName = "Buttslappers";
                    returnedTeam.totalPoints = totalPoints;
                    returnedTeam.teamId = 0;
                }

            }catch(Exception e){
                e.printStackTrace();
            }

            return returnedTeam;
        }
        @Override
        protected void onPostExecute(Team _team) {
            progressDialog.dismiss();
            getCallback.done(_team);
            super.onPostExecute(_team);
        }
    }
}