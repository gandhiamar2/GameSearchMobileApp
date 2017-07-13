package com.example.gandh.inclass06;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Similar_games extends AppCompatActivity {


    ArrayList<Gameslist> gameslists ;
    Game game;
    int id;
    TextView t1,t2;
    Button  finish;
    ListView lv;
    ArrayList<game_similar> gs;
    game_similar g1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similargames);
        setTitle("Similar Games");
        t1 = (TextView) findViewById(R.id.header);
        lv = (ListView) findViewById(R.id.ls2);
        finish = (Button) findViewById(R.id.finish) ;
        id =getIntent().getExtras().getInt("key3");
        gameslists = (ArrayList<Gameslist>) getIntent().getExtras().getSerializable("key");
        game = (Game) getIntent().getExtras().getSerializable("key1");
        gameslists.get(id).similar_ids = game.getSimilar_ids();
        gs = new ArrayList<>();
        t1.setText(game.getGame_title());
        for (int i=1; i<game.similar_ids.size();i++ ) {
            for (Gameslist g :
                    gameslists) {

                if(game.similar_ids.get(i).equals(g.getId()))
                {
                    g1 = new game_similar();
                   g1.setGame_title(g.getGame_title());
                    g1.setRelease_date(g.getRelease_date());
                    g1.setPlatform(g.getPlatform());
                    gs.add(g1);
                    Log.d("demo",gs.size()+"dsfdsa"+g1.getGame_title());
                }


            }

        }

        Adaptor_similarlist adaptor1 = new Adaptor_similarlist(this,R.layout.list_similar,gs);
        lv.setAdapter(adaptor1);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               Intent ic = new Intent(Similar_games.this, Activity_game.class);
               ic.putExtra("key6","fromsimiar");
                ic.putExtra("key", position);  //changed from index
                ic.putExtra("key1", gameslists);

                startActivity(ic);

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
