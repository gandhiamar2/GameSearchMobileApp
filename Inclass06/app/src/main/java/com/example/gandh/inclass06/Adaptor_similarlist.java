package com.example.gandh.inclass06;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gandh on 2/20/2017.
 */

public class Adaptor_similarlist extends ArrayAdapter {

    Context context;
    List<game_similar> games ;
    ImageView image;
    TextView text;
    int id2;
    game_similar game;

    public Adaptor_similarlist(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.games = objects;
        this.context= context;
    }
        
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_similar, parent, false);
            }

            text = (TextView) convertView.findViewById(R.id.text_similar);
            game = games.get(position);
            if (game.getRelease_date().length() > 4) {
                text.setText(game.getGame_title() + ". Released in " + game.getRelease_date().substring(6, 10) + ". Platfrom: " + game.getPlatform());
            } else {
                text.setText(game.getGame_title() + ". Released in " + game.getRelease_date() + ". Platfrom: " + game.getPlatform());
            }


            return convertView;

    }
}
