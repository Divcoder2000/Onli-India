package com.onli.india.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("Length_km")
    @Expose
    private String lengthKm;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("mapimage")
    @Expose
    private String mapimage;
    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;

    public Model(String name, String origin, String lengthKm, String end, String image, String mapimage, String wikipedia) {
        this.name = name;
        this.origin = origin;
        this.lengthKm = lengthKm;
        this.end = end;
        this.image = image;
        this.mapimage = mapimage;
        this.wikipedia = wikipedia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLengthKm() {
        return lengthKm;
    }

    public void setLengthKm(String lengthKm) {
        this.lengthKm = lengthKm;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMapimage() {
        return mapimage;
    }

    public void setMapimage(String mapimage) {
        this.mapimage = mapimage;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

}