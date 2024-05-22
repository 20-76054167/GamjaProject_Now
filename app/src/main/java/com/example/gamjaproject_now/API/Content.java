package com.example.gamjaproject_now.API;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("img")
    private String img;
    @SerializedName("description")
    private String description;
    @SerializedName("director")
    private String director;

    @SerializedName("actor")
    private String actor;
    @SerializedName("url")
    private String url;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String getUrl() {return url; }
    public void setUrl(String url) {this.url = url; }
    @Override
    public String toString(){
        if(title != null){
            title = "\"" + title + "\"";
        }
        if(img != null){
            img = "\"" + img + "\"";
        }
        if(description != null){
            description = "\"" + description + "\"";
        }
        if(director != null){
            director = "\"" + director + "\"";
        }
        if(actor != null){
            actor = "\"" + actor + "\"";
        }
        if(url != null){
            url = "\"" + url + "\"";
        }

        return "{" +
                "id=" + id +
                ", title=" + title +
                ", img=" + img +
                ", description=" + description +
                ", director= " + director +
                ", actor=" + actor +
                ", url=" + url +
                "}";
    }
}
