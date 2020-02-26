package com.meteo.meteo.data;

import javax.persistence.*;

@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_location")
    private int idLocation;
    private int idWeather;
    int timezone;
    String citta;
    String country;

    public Location() {
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(int idWeather) {
        this.idWeather = idWeather;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
