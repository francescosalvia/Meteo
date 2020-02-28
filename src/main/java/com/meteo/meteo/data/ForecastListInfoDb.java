package com.meteo.meteo.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="forecast_list_info")
public class ForecastListInfoDb {


    @Column(name = "id_forecast")
    private int idForecast;
    private String weatherDescription;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private int pressure;
    private int humidity;
    private LocalDateTime dtTxt;
    private int idList;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_elemento")
    private int idElemento;

    public ForecastListInfoDb() {
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public int getIdForecast() {
        return idForecast;
    }

    public void setIdForecast(int idForecast) {
        this.idForecast = idForecast;
    }

    public LocalDateTime getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(LocalDateTime dtTxt) {
        this.dtTxt = dtTxt;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

}
