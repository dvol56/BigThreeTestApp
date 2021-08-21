package com.example.bigthreetestapp;

import com.example.bigthreetestapp.model.CityModel;

import java.util.ArrayList;

public interface MainContract {
    interface View {
        void showCities(ArrayList<CityModel> cities);
    }

    interface Presenter {
        void loadCities();
    }
}
