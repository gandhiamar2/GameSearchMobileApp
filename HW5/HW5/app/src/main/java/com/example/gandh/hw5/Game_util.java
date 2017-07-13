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

public class Game_util {

    public static Game gamesparser(InputStream in) throws XmlPullParserException, IOException {
        XmlPullParser pull = XmlPullParserFactory.newInstance().newPullParser();
        pull.setInput(in, "UTF-8");
        ArrayList<Game> array_game = new ArrayList<>();
        Game game = null;
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

                        game = new Game();

                    }
                    else if(pull.getName().equals("GameTitle") && count1==0)
                    {
                         game.setGame_title(pull.nextText());
                        count1++;
                    }
                    else if(pull.getName().equals("Overview"))
                    {


                        game.setOverview(pull.nextText());
                    }
                    else if(pull.getName().equals("baseImgUrl"))
                    {


                        game.image.append(pull.nextText());
                    }
                    else if(pull.getName().equals("genre"))
                    {
                        game.setGenre(pull.nextText());

                    }
                    else if(pull.getName().equals("Publisher"))
                    {
                        game.setPublisher(pull.nextText());
                    }
                    else if(pull.getName().equals("Youtube"))
                    {
                        game.setYoutube(pull.nextText());
                    }
                    else if(pull.getName().equals("original") & count ==0)
                    {
                        game.image.append(pull.nextText());
                        count++;
                    }
                    else if(pull.getName().equals("boxart") & count ==0)
                    {
                        game.image.append(pull.nextText());

                        count++;
                    }
                    else if(pull.getName().equals("id")& game!=null)
                    {
                        game.similar_ids.add(pull.nextText());
                    }
                }
                break;

                case XmlPullParser.END_TAG:
                {
                    if(pull.getName().equals("Data"))
                    {
                        array_game.add(game);

                        Log.d("demo",game.getGenre()+"");
                        Log.d("demo",game.getSimilar_ids()+"");
                        Log.d("demo",game.getYoutube()+"");
                        Log.d("demo",game.getImage().toString()+"");

                    }
                }
                break;
            }

        event = pull.next();
        }

        return game;
    }
}
