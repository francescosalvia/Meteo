package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Coordinate {

    private float lat;
    private float lon;
    public Coordinate() {
    }


    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

}
