package dev.luisinder.theweather.database;

/**
 * Created by Luis on 07/09/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dev.luisinder.theweather.model.History;

public class DBController extends SQLiteOpenHelper {


    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "history.db";
    private static final String HISTORY_TABLE = "CREATE TABLE history ( cityId INTEGER PRIMARY KEY, cityName TEXT, north TEXT, south TEXT, east TEXT, west TEXT)";
    private static final String FIRST_RECORD = "INSERT INTO history (cityName) VALUES ('Madrid')";




    public DBController(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HISTORY_TABLE);
        db.execSQL(FIRST_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + HISTORY_TABLE);
        onCreate(db);
    }




    public void insertHistory(String name, String north, String south, String east, String west) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("cityName", name);
            valores.put("north", north);
            valores.put("south", south);
            valores.put("east", west);
            valores.put("west", east);
            db.insert("history", null, valores);
        }
        db.close();
    }


    public List<History> getHistory() {
        SQLiteDatabase db = getReadableDatabase();
        List<History> lista_contactos = new ArrayList<History>();
        String[] valores_recuperar = {"cityName", "north", "south", "east", "west"};
        String query = "SELECT * FROM history";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        do {
            History contactos = new History(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5));
            lista_contactos.add(contactos);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_contactos;
    }
}
