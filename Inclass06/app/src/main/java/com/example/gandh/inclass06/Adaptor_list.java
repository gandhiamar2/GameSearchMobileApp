package com.example.gandh.inclass06;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gandh on 2/20/2017.
 */

public class Adaptor_list extends ArrayAdapter {
    Context context;
    List<Gameslist> games ;
    ImageView image;
    TextView text;
    Gameslist game;
    MainActivity mn = new MainActivity();

    public Adaptor_list(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.games = objects;
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_layout,parent,false);
        }

            image = (ImageView) convertView.findViewById(R.id.image_list);
            text = (TextView) convertView.findViewById(R.id.text_list);
            game =  games.get(position);
        if(game.bit!=null)
            image.setImageBitmap(game.bit);
        if(game.getRelease_date().length()>4)
        {
            text.setText(game.getGame_title() + ". Released in " + game.getRelease_date().substring(6,10) + ". Platfrom: " + game.getPlatform());
        }
        else {
            text.setText(game.getGame_title() + ". Released in " + game.getRelease_date() + ". Platfrom: " + game.getPlatform());
        }
        //rb.setId(Integer.parseInt(game.getId()));

        return convertView;
    }
}
