package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="location")
public class LocationDb {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_location")
    private int idLocation;
    private int idWeather;
    int timezone;
    String citta;
    String country;
    private String tipo;
    private LocalDateTime scadenza;
    @Column(name = "zip_code")
    private String zipCode;

    public LocationDb() {
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}


