package com.example.gandh.hw5;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Gameslist_async.gameslist_interface {
    EditText et1;
    Button go, search;
    ProgressBar pb;
    LinearLayout l1;
    RadioGroup RG;
    int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = (LinearLayout) findViewById(R.id.ll1);
        if(connection())
        {
            pb = (ProgressBar) findViewById(R.id.pb1);
            pb.setVisibility(pb.INVISIBLE);
            et1 = (EditText) findViewById(R.id.edittext1);
            go = (Button) findViewById(R.id.button2);
            go.setEnabled(false);

            search = (Button) findViewById(R.id.search);
            go = (Button) findViewById(R.id.button2);

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(et1.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Please fill the keyword",Toast.LENGTH_LONG).show();
                    }
                    else {
                        pb.setVisibility(pb.VISIBLE);
                        l1.removeAllViews();
                        new Gameslist_async(MainActivity.this).execute(et1.getText().toString());
                    }
                }
            });

        }

    }

    boolean connection()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nw = cm.getActiveNetworkInfo();
        if(nw!= null&& nw.isConnected())
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public void gamelist_data(final ArrayList<Gameslist> gameslists) throws ParseException {
        if( gameslists==null || gameslists.size()==0 )
        {
            pb.setVisibility(pb.INVISIBLE);
           // Toast.makeText(getApplicationContext(),"No Related Games are Available",Toast.LENGTH_LONG).show();
            TextView t=new TextView(this);
            t.setText("No Related Games are Available or API error");
            l1.removeAllViews();
            l1.addView(t);
        }
        else {
            pb.setVisibility(pb.INVISIBLE);

            l1.removeAllViews();
            RG = new RadioGroup(this);
            RG.clearCheck();
            RG.removeAllViews();
            RadioButton rb;

            for (Gameslist game :
                    gameslists) {
                rb = new RadioButton(this);
                if(game.getRelease_date().length()>4)
                {
                    rb.setText(game.getGame_title() + ". Released in " + game.getRelease_date().substring(6,10) + ". Platfrom: " + game.getPlatform());
                }
                else {
                    rb.setText(game.getGame_title() + ". Released in " + game.getRelease_date() + ". Platfrom: " + game.getPlatform());
                }
                rb.setId(Integer.parseInt(game.getId()));
                RG.addView(rb);
            }

            l1.addView(RG);
            go.setEnabled(true);

            RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    checked = checkedId;
                }
            });

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (RG.getCheckedRadioButtonId() >= 0) {
                        Log.d("demo", RG.getCheckedRadioButtonId() + "");
                        Intent ia = new Intent(MainActivity.this, Activity_game.class);
                        ia.putExtra("key", checked);
                        ia.putExtra("key1", gameslists);

                        startActivity(ia);
                    } else
                        Toast.makeText(MainActivity.this, " select a radio button", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
