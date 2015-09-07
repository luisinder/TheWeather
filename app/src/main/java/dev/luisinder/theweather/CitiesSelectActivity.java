package dev.luisinder.theweather;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dev.luisinder.theweather.adapter.CityAdapter;
import dev.luisinder.theweather.database.DBController;
import dev.luisinder.theweather.service.GsonRequest;
import dev.luisinder.theweather.service.RequestQueueCompass;
import dev.luisinder.theweather.service.response.CityResponse;
import dev.luisinder.theweather.service.response.ConditionsResponse;
import dev.luisinder.theweather.utils.Logger;
import dmax.dialog.SpotsDialog;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * Created by Luis on 04/09/2015.
 */
public class CitiesSelectActivity extends BaseActivity implements com.android.volley.Response.Listener<ConditionsResponse>,Response.ErrorListener{

    private ListView cityList;
    private CityAdapter adapter;
    private ArrayList<CityResponse> city_data;
    private ArrayList<ConditionsResponse> mCity;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DBController MDB = new DBController(getApplicationContext());
        setContentView(R.layout.citieselect_activity);
        mDialog = new SpotsDialog(mContext);
        cityList = (ListView) findViewById(R.id.list_city);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("response", null);
        Type type = new TypeToken<ArrayList<CityResponse>>() {}.getType();
        city_data = gson.fromJson(json, type);
        adapter = new CityAdapter(city_data,mContext);
        cityList.setAdapter(adapter);

        cityList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Logger.v("City selected", city_data.get(position).name);
                MDB.insertHistory(city_data.get(position).name,String.valueOf(city_data.get(position).bbox.north), String.valueOf(city_data.get(position).bbox.south),
                        String.valueOf(city_data.get(position).bbox.east), String.valueOf(city_data.get(position).bbox.west));
                searchCity(city_data.get(position).bbox.north, city_data.get(position).bbox.south, city_data.get(position).bbox.east, city_data.get(position).bbox.west);
            }
        });
    }

    protected void searchCity(float north, float south, float east, float west)
    {
        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("api.geonames.org")
                .path("weatherJSON")
                .appendQueryParameter("north", String.valueOf(north))
                .appendQueryParameter("south", String.valueOf(south))
                .appendQueryParameter("east", String.valueOf(east))
                .appendQueryParameter("west", String.valueOf(west))
                .appendQueryParameter("username", "ilgeonamessample")
                .build();
        Logger.v("Request: " + uri.toString());
        mDialog.show();
        GsonRequest request = new GsonRequest<>(uri.toString(), ConditionsResponse.class, null, this, this);
        RequestQueueCompass.getRequestQueue().add(request);
    }

    @Override
    public void onResponse(ConditionsResponse response) {
        mCity = (ArrayList) response.weatherObservations;
        mDialog.dismiss();
        if(mCity.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "No existen datos para esa localidad", Toast.LENGTH_LONG).show();
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(mCity);
            Intent intent = new Intent(mActivity, DetailsActivity.class);
            intent.putExtra("info", json);
            startActivity(intent);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
