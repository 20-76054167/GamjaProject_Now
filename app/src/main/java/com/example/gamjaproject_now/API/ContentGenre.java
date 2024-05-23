package com.example.gamjaproject_now.API;

import com.google.gson.annotations.SerializedName;

public class ContentGenre {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    @SerializedName("id")
    private int id;


    @SerializedName("genre_id")
    private int genre_id;

    @Override
    public String toString() {
        return "ContentGenre{" +
                "id=" + id +
                ", genre_id=" + genre_id +
                '}';
    }
}
