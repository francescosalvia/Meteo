package com.meteo.meteo.data;

import java.util.ArrayList;
import java.util.List;

public class Forecast {


    private Coordinate coordinate;
    private Location location;
    private int sunrise;
    private int sunset;
    private List<ForecastListInfo> listInfo;


    public List<ForecastListInfo> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<ForecastListInfo> listInfo) {
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


