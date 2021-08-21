package com.example.bigthreetestapp.presenter;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.bigthreetestapp.MainContract;
import com.example.bigthreetestapp.model.CityModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    final Gson gson = new Gson();
    final Context context;
    public MainContract.View view;

    public MainPresenter(MainContract.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadCities() {
        Type citiesType = new TypeToken<ArrayList<CityModel>>() {}.getType();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream ims = assetManager.open("cities.json");
            Reader reader = new InputStreamReader(ims);
            view.showCities(gson.fromJson(reader, citiesType));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
