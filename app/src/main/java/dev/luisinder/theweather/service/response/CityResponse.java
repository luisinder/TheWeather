package dev.luisinder.theweather.service.response;

import java.io.Serializable;

/**
 * Created by Luis on 04/09/2015.
 */
public class CityResponse implements Serializable {
    public CityResponse(){
        bbox = new CompassResponse();
    }

    public float lat;
    public float lng;

    public CompassResponse bbox;

    public String countryName;
    public String name;
    public String asciiName;
    public long geonameId;
}
