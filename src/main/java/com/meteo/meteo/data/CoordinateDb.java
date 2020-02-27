package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="coordinate")
public class CoordinateDb {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_coord")
    private int idCoord;
    private float lat;
    private float lon;
    private int idWeather;
    private String tipo;
    private LocalDateTime scadenza;
    public CoordinateDb() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
        this.scadenza = scadenza;
    }
}
