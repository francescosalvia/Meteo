package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;


public class Location {

    int timezone;
    String citta;
    String country;


    public Location() {
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
