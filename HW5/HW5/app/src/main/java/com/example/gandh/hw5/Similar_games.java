package com.example.gandh.hw5;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Similar_games extends AppCompatActivity {

    LinearLayout l3;
    ArrayList<Gameslist> gameslists;
    Game game;
    int id;
    TextView t1,t2;
    Button  finish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similargames);
        l3 = (LinearLayout) findViewById(R.id.ll3);
        t1 = (TextView) findViewById(R.id.header);
        finish = (Button) findViewById(R.id.finish) ;
        gameslists = (ArrayList<Gameslist>) getIntent().getExtras().getSerializable("key");
        game = (Game) getIntent().getExtras().getSerializable("key1");
        id =getIntent().getExtras().getInt("key3");
        t1.setText(game.getGame_title());
        for (int i=1; i<game.similar_ids.size();i++ ) {
            for (Gameslist g :
                    gameslists) {

                if(game.similar_ids.get(i).equals(g.getId()))
                {
                    TextView t = new TextView(this);
                    t.setTypeface(t.getTypeface(),Typeface.BOLD);
                    if(g.getRelease_date().length()>4)
                    t.setText(g.getGame_title()+". Released in "+g.getRelease_date().substring(6,10)+". Platfrom: "+g.getPlatform());
                    else
                    {
                        t.setText(g.getGame_title()+". Released in "+g.getRelease_date()+". Platfrom: "+g.getPlatform());
                    }
                    l3.addView(t);

                }
            }

        }

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
