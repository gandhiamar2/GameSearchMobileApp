package com.example.gandh.inclass06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by gandh on 2/20/2017.
 */

public class Imagelist_Async extends AsyncTask<String, Void, Bitmap>{

    setimageicon activity;

    public Imagelist_Async(setimageicon activity)
    {
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params)
    {

        URL url = null;
        URL url1= null;
        try {
            url = new URL("http://thegamesdb.net/api/GetGame.php?id="+params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String ur = Imagelist_util.gamesparser(con.getInputStream());
            if(ur.equals("http://thegamesdb.net/banners/")|| ur==null)
            {
                return null;
            }
            else{
            url1 = new URL(ur);
            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("GET");
            Bitmap bit = BitmapFactory.decodeStream(con1.getInputStream());
            return bit;}

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return null;

    }

    @Override
    protected void onPostExecute(Bitmap bit) {
        activity.settingimageicon(bit);

    }

    public interface setimageicon{

        public void settingimageicon(Bitmap bit);
    }
}


