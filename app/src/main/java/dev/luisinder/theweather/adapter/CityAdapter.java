package dev.luisinder.theweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.luisinder.theweather.R;
import dev.luisinder.theweather.service.response.CityResponse;

/**
 * Created by Luis on 04/09/2015.
 */
public class CityAdapter extends BaseAdapter {

    public ArrayList<CityResponse> locations;
    private Context context;

    public CityAdapter(ArrayList<CityResponse> locations, Context context){
        this.context = context;
        this.locations = locations;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(R.layout.city_item, null);

        CityResponse location = locations.get(position);

        TextView itemText = (TextView)rootView.findViewById(R.id.txt_cityname);
        itemText.setText(location.name + " (" + location.countryName + ")");
        TextView details = (TextView)rootView.findViewById(R.id.txt_extradata);
        details.setText(location.lat + " - " + location.lng);
        return rootView;
    }
}
