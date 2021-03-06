package dev.luisinder.theweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dev.luisinder.theweather.R;
import dev.luisinder.theweather.model.History;

/**
 * Created by Luis on 07/09/2015.
 */
public class HistoryAdapter extends BaseAdapter {
    public List<History> locations;
    private Context context;

    public HistoryAdapter(List<History> locations, Context context){
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

        History location = locations.get(position);

        TextView itemText = (TextView)rootView.findViewById(R.id.txt_cityname);
        itemText.setText(location.getName());
        return rootView;
    }

}
