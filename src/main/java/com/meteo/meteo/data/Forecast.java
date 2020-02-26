package com.meteo.meteo.data;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Forecast {


    private Coordinate coordinate;
    private Location location;
    private int sunrise;
    private int sunset;
    private ArrayList<ForecastListInfo> listInfo;


    public ArrayList<ForecastListInfo> getListInfo() {
        return listInfo;
    }

    public void setListInfo(ArrayList<ForecastListInfo> listInfo) {
        this.listInfo = listInfo;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}


