package dev.luisinder.theweather;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dev.luisinder.theweather.utils.Logger;

/**
 * Created by Luis on 07/09/2015.
 */
public class DetailsActivity extends BaseActivity implements OnMapReadyCallback {

    @InjectView(R.id.txt_name)
    protected TextView mCity;
    @InjectView(R.id.clouds)
    protected TextView mCloud;
    protected float lat;
    protected float lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.inject(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("info");
        value = value.replace("[","").replace("]","");
        Logger.v("EXTRA", value);
        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressbar_temperature);


        try {

            JSONObject obj = new JSONObject(value);
            mCity.setText(obj.getString("stationName"));
            mCloud.setText("Humedad " + obj.getString("humidity") +  "%" + " - Temperatura: " + obj.getString("temperature")+ "º");
            lat = Float.valueOf(obj.getString("lat"));
            lng = Float.valueOf(obj.getString("lng"));
            mProgress.setProgress(Integer.parseInt(obj.getString("temperature")));
            mProgress.setMax(100);

        } catch (Throwable t) {
            Log.e("TheWeather", "Could not parse malformed JSON: \"" + value + "\"");
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng point = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(point));
        map.moveCamera(CameraUpdateFactory.newLatLng(point));
    }
}
