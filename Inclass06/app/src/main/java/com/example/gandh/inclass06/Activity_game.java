package com.example.gandh.inclass06;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Activity_game extends AppCompatActivity implements Gameselected_async.game_interface,Imageasync.setimage {
    LinearLayout l2;
    ArrayList<Gameslist> gameslists;
    TextView t1,t2,t3,t5;
    ImageView iv;
    Button  similar, finish;
    int id;
    ProgressBar pb2;
String key6 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Game Details");
        setContentView(R.layout.game_details);
        l2 = (LinearLayout) findViewById(R.id.ll2);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        similar = (Button) findViewById(R.id.similargames) ;
        finish = (Button) findViewById(R.id.finish) ;

        iv = (ImageView) findViewById(R.id.imageView);
        t1 = (TextView) findViewById(R.id.gametitle);
        t2 = (TextView) findViewById(R.id.genre);
        t5= (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.publisher);
         id =getIntent().getExtras().getInt("key");

        finish.setEnabled(false);
        similar.setEnabled(false);
        pb2.setVisibility(pb2.VISIBLE);
        gameslists = (ArrayList<Gameslist>) getIntent().getExtras().getSerializable("key1");

        new Gameselected_async(Activity_game.this).execute(gameslists.get(id).getId());

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        key6 = getIntent().getExtras().getString("key6");
        if(key6==null)
            key6= "hello";

    }

    @Override
    public void game_data(final Game gamedata)  {
        l2.removeAllViews();

        new Imageasync(this).execute(gamedata.getImage().toString());
        t1.setText(gamedata.getGame_title());
        t2.setText("Genre: "+gamedata.getGenre());
        t3.setText("Publisher: "+gamedata.getPublisher());
        TextView t4 = new TextView(this);
        t4.setText(gamedata.getOverview());
        t5.setText("Overview: ");
        l2.addView(t4);

        similar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent similar = new Intent(Activity_game.this,Similar_games.class);
                similar.putExtra("key1",  gamedata);
                similar.putExtra("key",gameslists);
                similar.putExtra("key3",id);
                startActivity(similar);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void settingimage(Bitmap bit) {
        iv.setImageBitmap(bit);
        pb2.setVisibility(pb2.INVISIBLE);

        finish.setEnabled(true);
        if(key6.equals("fromsimiar"))
        {
            similar.setVisibility(similar.INVISIBLE);
        }
        else
        similar.setEnabled(true);
    }
}
