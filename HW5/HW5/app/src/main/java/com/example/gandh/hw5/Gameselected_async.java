package com.example.gandh.hw5;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Gameselected_async extends AsyncTask<Integer,Void,Game> {
game_interface activity;

    public Gameselected_async(game_interface activity) {
        this.activity = activity;
    }

    @Override
    protected Game doInBackground(Integer... params) {
        URL url = null;

        try {
            url = new URL("http://thegamesdb.net/api/GetGame.php?id="+params[0]);
            Log.d("demo","http://thegamesdb.net/api/GetGame.php?id="+params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();

            con.setRequestMethod("GET");
            InputStream is =  con.getInputStream();
            return Game_util.gamesparser(is);

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
    protected void onPostExecute(Game gamedata) {
        try {
            activity.game_data(gamedata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface game_interface {

        public void game_data(Game gamedata) throws IOException;
    }
}