package com.example.gandh.inclass06;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gandh.inclass06.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Gameslist_async.gameslist_interface,Imagelist_Async.setimageicon
{
    EditText et1;
    Button search;
    ProgressBar pb;
    int checked;
    ListView lv;
    Adaptor_list adaptor;
    ArrayList<Bitmap> imgarray = new ArrayList<>();
    ArrayList<Gameslist> gameslists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("The Games DB");
        // l1 = (LinearLayout) findViewById(R.id.ll1);
        if (connection()) {
            pb = (ProgressBar) findViewById(R.id.pb1);
            pb.setVisibility(pb.INVISIBLE);
            et1 = (EditText) findViewById(R.id.edittext1);
            lv = (ListView) findViewById(R.id.ls1);


            search = (Button) findViewById(R.id.search);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et1.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill the keyword", Toast.LENGTH_LONG).show();
                    } else {
                        pb.setVisibility(pb.VISIBLE);
                        // l1.removeAllViews();
                        new Gameslist_async(MainActivity.this).execute(et1.getText().toString());
                    }
                }
            });

        }

    }

    boolean connection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nw = cm.getActiveNetworkInfo();
        if (nw != null && nw.isConnected()) {
            return true;
        } else
            return false;
    }

    @Override
    public void gamelist_data(final ArrayList<Gameslist> gameslists) throws ParseException {
        if (gameslists == null || gameslists.size() == 0) {
            pb.setVisibility(pb.INVISIBLE);
            // Toast.makeText(getApplicationContext(),"No Related Games are Available",Toast.LENGTH_LONG).show();
            //TextView t=new TextView(this);
            //t.setText("No Related Games are Available or API error");
            //l1.removeAllViews();

        } else {
            this.gameslists = gameslists;
            imgarray = new ArrayList<>();
            if (gameslists.size() > 11) {
                for (int i = 0; i < 10; i++) {
                    new Imagelist_Async(this).execute(gameslists.get(i).getId());
                }
            } else {
                for (int i = 0; i < gameslists.size(); i++) {
                    new Imagelist_Async(this).execute(gameslists.get(i).getId());
                }
            }

            adaptor = new Adaptor_list(this, R.layout.list_layout, gameslists);
            // lv.setAdapter(adaptor);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent ia = new Intent(MainActivity.this, Activity_game.class);
                    ia.putExtra("key", position);  //changed from index
                    ia.putExtra("key1", gameslists);
                    startActivity(ia);

                }
            });
        }
    }

    @Override
    public void settingimageicon(Bitmap bit) {
        if (bit != null)
            imgarray.add(bit);
        else
            imgarray.add(null);
        Log.d("demo", imgarray.size() + "");

        if (imgarray.size() == 10 || gameslists.size() == imgarray.size()) {
            if (gameslists.size() > 11) {
                for (int i = 0; i < 10; i++) {
                    gameslists.get(i).bit = imgarray.get(i);
                }

            }
            else {

                    for (int i = 0; i < imgarray.size(); i++) {
                        gameslists.get(i).bit = imgarray.get(i);
                    }
                }
                lv.setAdapter(adaptor);
                pb.setVisibility(pb.INVISIBLE);
            }
        }
    }
