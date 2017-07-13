package com.example.gandh.hw5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gandh on 2/17/2017.
 */

public class Game implements Serializable {

    String game_title, overview, genre, publisher, youtube;
    StringBuilder image = new StringBuilder();

    public ArrayList<String> getSimilar_ids() {
        return similar_ids;
    }

    public ArrayList<String> similar_ids = new ArrayList<>();

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public StringBuilder getImage() {
        return image;
    }


}
