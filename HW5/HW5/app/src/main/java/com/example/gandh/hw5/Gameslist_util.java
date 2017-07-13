package com.example.gandh.hw5;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Gameslist_util {



    public static ArrayList<Gameslist> gameslistsparser(InputStream in) throws XmlPullParserException, IOException {
        XmlPullParser pull = XmlPullParserFactory.newInstance().newPullParser();
        pull.setInput(in, "UTF-8");
        ArrayList<Gameslist> array_games = new ArrayList<>();
        Gameslist games = null;
        int event = pull.getEventType();

        while(event != XmlPullParser.END_DOCUMENT)
        {
            switch (event)
            {
                case XmlPullParser.START_TAG:
                {
                    if(pull.getName().equals("Game"))
                    {
                        games = new Gameslist();
                    }
                    else if (pull.getName().equals("id"))
                    {
                        games.setId(pull.nextText().trim());
                    }
                    else if (pull.getName().equals("GameTitle"))
                    {
                        games.setGame_title(pull.nextText().trim());
                    }
                    else if (pull.getName().equals("ReleaseDate"))
                    {
                        games.setRelease_date(pull.nextText().trim());
                    }
                    else if (pull.getName().equals("Platform"))
                    {
                        games.setPlatform(pull.nextText().trim());
                    }
                }
                break;

                case XmlPullParser.END_TAG:
                {
                    if(pull.getName().equals("Game"))
                    {
                        array_games.add(games);

                        games = null;
                    }
                }
                break;
            }

            event = pull.next();
        }

        return array_games;
    }
}
