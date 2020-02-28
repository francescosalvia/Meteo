package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="forecast")
public class ForecastDb {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_forecast")
    private int id;
    private int sunrise;
    private int sunset;
    @Column(name = "tipo")
    private String tipoMetodo;
    private LocalDateTime scadenza;


    public ForecastDb() {
    }

    public String getTipoMetodo() {
        return tipoMetodo;
    }

    public void setTipoMetodo(String tipoMetodo) {
        this.tipoMetodo = tipoMetodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
        this.scadenza = scadenza;
    }
}
