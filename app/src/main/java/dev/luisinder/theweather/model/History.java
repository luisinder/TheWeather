package dev.luisinder.theweather.model;

/**
 * Created by Luis on 07/09/2015.
 */
public class History {
    private int id;
    private String name;
    private String north;
    private String south;
    private String east;
    private String west;

    public History(int id, String name, String north, String south, String east, String west) {
        this.id = id;
        this.name = name;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }



    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String mName) {
        this.name = mName;
    }


    public String getNorth() {
        return north;
    }
    public void setNorth(String mNorth) {
        this.north = mNorth;
    }


    public String getSouth() {
        return south;
    }
    public void setSouth(String mSouth) {
        this.south = mSouth;
    }


    public String getEeast() {
        return east;
    }
    public void setEast(String mEast) {
        this.east = mEast;
    }


    public String getWest() {
        return west;
    }
    public void setWest(String mWest) {
        this.west = mWest;
    }
}
