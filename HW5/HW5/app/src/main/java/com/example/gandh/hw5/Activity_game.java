package com.example.gandh.hw5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Activity_game extends AppCompatActivity implements Gameselected_async.game_interface,Imageasync.setimage {
    LinearLayout l2;
    ArrayList<Gameslist> gameslists;
    TextView t1,t2,t3,t5;
    ImageView iv;
    Button trailer, similar, finish;
    int id;
    ProgressBar pb2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_details);
        l2 = (LinearLayout) findViewById(R.id.ll2);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        similar = (Button) findViewById(R.id.similargames) ;
        finish = (Button) findViewById(R.id.finish) ;
        trailer = (Button) findViewById(R.id.trailer) ;
        iv = (ImageView) findViewById(R.id.imageView);
        t1 = (TextView) findViewById(R.id.gametitle);
        t2 = (TextView) findViewById(R.id.genre);
        t5= (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.publisher);
         id =getIntent().getExtras().getInt("key");
        trailer.setEnabled(false);
        finish.setEnabled(false);
        similar.setEnabled(false);
        pb2.setVisibility(pb2.VISIBLE);
        gameslists = (ArrayList<Gameslist>) getIntent().getExtras().getSerializable("key1");

        new Gameselected_async(Activity_game.this).execute(id);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(gamedata.getYoutube()!=null)
            {

                Intent trailer = new Intent(Activity_game.this, Video.class);
                trailer.putExtra("key", gamedata.getYoutube());
                trailer.putExtra("key1", gamedata.game_title);
                startActivity(trailer);
            }
                else
            {
                Toast.makeText(Activity_game.this,"no trailer present",Toast.LENGTH_SHORT).show();
            }
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
        trailer.setEnabled(true);
        finish.setEnabled(true);
        similar.setEnabled(true);
    }
}
