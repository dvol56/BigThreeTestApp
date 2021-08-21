package com.example.bigthreetestapp.model;

import com.google.gson.annotations.SerializedName;

public class CityModel {
    @SerializedName(value = "country")
    public String country;
    @SerializedName(value = "name")
    public String name;
    @SerializedName(value = "coord")
    public CityCoordModel coord;

    public String getName(){
        return this.name;
    }
}
