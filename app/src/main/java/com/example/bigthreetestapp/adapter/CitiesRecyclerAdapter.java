package com.example.bigthreetestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bigthreetestapp.R;
import com.example.bigthreetestapp.model.CityModel;
import java.util.ArrayList;
import java.util.Locale;

public class CitiesRecyclerAdapter extends RecyclerView.Adapter<CitiesRecyclerAdapter.CitiesViewHolder> implements Filterable {

    private final LayoutInflater inflater;
    private final ArrayList<CityModel> cities;
    private final ArrayList<CityModel> fullCitiesList;

    public CitiesRecyclerAdapter(Context context, ArrayList<CityModel> cities) {
        this.cities = cities;
        this.inflater = LayoutInflater.from(context);
        fullCitiesList = new ArrayList<>(cities);
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_city, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        CityModel city = cities.get(position);
        holder.title.setText(city.country + ", " + city.name);
        holder.subTitle.setText(city.coord.lat.toString() + city.coord.lon.toString());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CityModel> filteredCitiesList = new ArrayList<>();
            if (constraint.length() == 0){
                filteredCitiesList.addAll((ArrayList<CityModel>) fullCitiesList);
            }else {
                String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
                for(CityModel cm : fullCitiesList){
                    if (cm.name.toLowerCase(Locale.ROOT).contains(filterPattern)){
                        filteredCitiesList.add(cm);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredCitiesList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cities.clear();
            cities.addAll((ArrayList<CityModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView subTitle;
        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cityTitle);
            subTitle = (TextView) itemView.findViewById(R.id.citySubTitle);
        }
    }
}
