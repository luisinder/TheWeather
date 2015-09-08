package dev.luisinder.theweather;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dev.luisinder.theweather.adapter.HistoryAdapter;
import dev.luisinder.theweather.database.DBController;
import dev.luisinder.theweather.model.History;
import dev.luisinder.theweather.service.GsonRequest;
import dev.luisinder.theweather.service.RequestQueueCompass;
import dev.luisinder.theweather.service.response.CityNameResponse;
import dev.luisinder.theweather.service.response.CityResponse;
import dev.luisinder.theweather.utils.Logger;
import dmax.dialog.SpotsDialog;

public class MainActivity extends BaseActivity implements com.android.volley.Response.Listener<CityNameResponse>,Response.ErrorListener{

    @InjectView(R.id.search_field)
    protected EditText mSearchCity;
    private ArrayList<CityResponse> selectCity;
    private ListView cityList;
    private HistoryAdapter adapter;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mDialog = new SpotsDialog(mContext);

        // Acceso a la base de datos
        DBController MDB = new DBController(getApplicationContext());
        final List<History> mHisCity = new ArrayList<History>(MDB.getHistory());
        cityList = (ListView) findViewById(R.id.list_history);
        adapter = new HistoryAdapter(mHisCity,mContext);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Logger.v("City selected", mHisCity.get(position).getName());
                URLBuilder(mHisCity.get(position).getName());
            }
        });
    }

    @OnClick(R.id.relative_confirm)
    protected void search() {
        String city = mSearchCity.getText().toString();
        URLBuilder(city);
    }

    protected void URLBuilder(String city)
    {
        mDialog.show();

        int maxRows = 20;
        int startRow = 0;
        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("api.geonames.org")
                .path("searchJSON")
                .appendQueryParameter("q", city)
                .appendQueryParameter("maxRows", String.valueOf(maxRows))
                .appendQueryParameter("startRow", String.valueOf(startRow))
                .appendQueryParameter("lang", "en")
                .appendQueryParameter("isNameRequired", "true")
                .appendQueryParameter("style", "FULL")
                .appendQueryParameter("username", "ilgeonamessample")
                .build();
        Logger.i("Request: " + uri.toString());

        GsonRequest request = new GsonRequest<>(uri.toString(), CityNameResponse.class, null, this, this);
        RequestQueueCompass.getRequestQueue().add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(CityNameResponse response) {
        selectCity = (ArrayList) response.geonames;
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(selectCity);

        editor.putString("response", json);
        editor.commit();
        Intent intent = new Intent(mActivity, CitiesSelectActivity.class);
        mDialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


}

