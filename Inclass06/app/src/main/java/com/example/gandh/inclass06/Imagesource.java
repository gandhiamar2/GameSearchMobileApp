package com.example.gandh.inclass06;

import java.io.Serializable;

/**
 * Created by gandh on 2/20/2017.
 */

public class Imagesource implements Serializable {

    String id;
    StringBuilder image = new StringBuilder();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StringBuilder getImage() {
        return image;
    }

    public void setImage(StringBuilder image) {
        this.image = image;
    }
}
