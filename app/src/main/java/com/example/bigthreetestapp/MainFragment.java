package com.example.bigthreetestapp;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bigthreetestapp.adapter.CitiesRecyclerAdapter;
import com.example.bigthreetestapp.model.CityModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    final Gson gson = new Gson();
    private TextInputEditText input;
    private CitiesRecyclerAdapter adapter;
    private ArrayList<CityModel> cities;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        input = view.findViewById(R.id.searchEditText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Type citiesType = new TypeToken<ArrayList<CityModel>>() {}.getType();
        try {
            AssetManager assetManager = requireActivity().getAssets();
            InputStream ims = assetManager.open("cities.json");
            Reader reader = new InputStreamReader(ims);
            cities = gson.fromJson(reader, citiesType);
        } catch (IOException e){
            e.printStackTrace();
        }
        RecyclerView citiesRecycler = (RecyclerView) view.findViewById(R.id.citiesRecycler);
        adapter = new CitiesRecyclerAdapter(requireContext(), cities);
        citiesRecycler.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });
    }
}
