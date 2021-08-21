package com.example.bigthreetestapp.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bigthreetestapp.MainContract;
import com.example.bigthreetestapp.R;
import com.example.bigthreetestapp.adapter.CitiesRecyclerAdapter;
import com.example.bigthreetestapp.model.CityModel;
import com.example.bigthreetestapp.presenter.MainPresenter;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new MainPresenter(this, requireContext());
        return inflater.inflate(R.layout.fragment_main,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadCities();
    }

    @Override
    public void showCities(ArrayList<CityModel> cities) {
        RecyclerView citiesRecycler = (RecyclerView) requireActivity().findViewById(R.id.citiesRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        CitiesRecyclerAdapter adapter = new CitiesRecyclerAdapter(requireContext(), cities);
        citiesRecycler.setLayoutManager(layoutManager);
        citiesRecycler.setAdapter(adapter);
        TextInputEditText inputField = requireActivity().findViewById(R.id.searchEditText);
        inputField.addTextChangedListener(new TextWatcher() {
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
