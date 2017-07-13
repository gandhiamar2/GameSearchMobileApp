package com.example.gandh.inclass06;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Gameslist_async extends AsyncTask<String,Void,ArrayList<Gameslist>> {
    gameslist_interface activity;

    public Gameslist_async(gameslist_interface activity) {
        this.activity = activity;
    }
    HttpURLConnection con;
    InputStream is;
    @Override
    protected ArrayList<Gameslist> doInBackground(String... params) {
        URL url = null;

        try {
            url = new URL("http://thegamesdb.net/api/GetGamesList.php?name="+params[0]);
            Log.d("demo","http://thegamesdb.net/api/GetGamesList.php?name="+params[0]);


                con = (HttpURLConnection) url.openConnection();
                con.connect();

                con.setRequestMethod("GET");

            is = con.getInputStream();
                return Gameslist_util.gameslistsparser(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Gameslist> gameslists) {
        try {
            activity.gamelist_data(gameslists);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

   public interface gameslist_interface {

        public void gamelist_data(ArrayList<Gameslist> gameslists) throws ParseException;
    }
}
