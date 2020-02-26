package com.meteo.meteo.data;

import javax.persistence.*;

@Entity
@Table(name="coordinate")
public class Coordinate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_coord")
    private int idCoord;
    private float lat;
    private float lon;
    private int idWeather;

    public Coordinate() {
    }

    public int getIdCoord() {
        return idCoord;
    }

    public void setIdCoord(int idCoord) {
        this.idCoord = idCoord;
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(int idWeather) {
        this.idWeather = idWeather;
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
