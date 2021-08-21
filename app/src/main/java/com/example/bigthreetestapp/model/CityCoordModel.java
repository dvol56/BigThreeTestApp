package com.example.bigthreetestapp.model;

import com.google.gson.annotations.SerializedName;

public class CityCoordModel {
    @SerializedName(value = "lon")
    public Double lon;
    @SerializedName(value = "lat")
    public Double lat;
}
