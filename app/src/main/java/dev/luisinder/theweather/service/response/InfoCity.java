package dev.luisinder.theweather.service.response;

/**
 * Created by Luis on 07/09/2015.
 */
public class InfoCity {
    public String temperature;
    public int humidity;
    public String windSpeed;

    public float lat;
    public float lng;

    public String stationName;

    public String getName(){
        return stationName;
    }
}
