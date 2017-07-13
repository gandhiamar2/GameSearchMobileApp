package com.example.gandh.inclass06;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Gameslist implements Serializable {
    String id, game_title, release_date, platform;
    public ArrayList<String> similar_ids = new ArrayList<>();

    transient Bitmap   bit;

    public String getId() {
        return id;
    }

    public ArrayList<String> getSimilar_ids() {
        return similar_ids;
    }

    public void setSimilar_ids(ArrayList<String> similar_ids) {
        this.similar_ids = similar_ids;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
