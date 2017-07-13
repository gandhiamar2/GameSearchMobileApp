package com.example.gandh.inclass06;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by gandh on 2/20/2017.
 */

public class Imagelist_util {


    public static String gamesparser(InputStream in) throws XmlPullParserException, IOException {
        XmlPullParser pull = XmlPullParserFactory.newInstance().newPullParser();
        pull.setInput(in, "UTF-8");
       // ArrayList<Imagesource> array = new ArrayList<>();
        Imagesource game = null;
        int count =0;
        int count1=0;
        int event = pull.getEventType();
        while(event != XmlPullParser.END_DOCUMENT)
        {
            switch (event)
            {
                case XmlPullParser.START_TAG:
                {
                    if(pull.getName().equals("Data"))
                    {

                        game = new Imagesource();

                    }
                    else if(pull.getName().equals("id") &&count1==0)
                    {


                        game.setId(pull.nextText());
                        count1++;
                    }
                    else if(pull.getName().equals("baseImgUrl"))
                    {


                        game.image.append(pull.nextText());
                    }
                    else if(pull.getName().equals("clearlogo") )
                    {

                        game.image.append(pull.nextText());

                    }

                    
                }
                break;

                case XmlPullParser.END_TAG:
                {
                    if(pull.getName().equals("Data"))
                    {
                       // array.add(game);
                        Log.d("demo",game.getImage().toString());

                        

                    }
                }
                break;
            }

            event = pull.next();
        }

        return game.getImage().toString();
    }
}


