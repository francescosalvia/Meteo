package com.meteo.meteo.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mtconfig")
public class MeteoProperties {


    private String apikey;
    private String urlWeather;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getUrlWeather() {
        return urlWeather;
    }

    public void setUrlWeather(String urlWeather) {
        this.urlWeather = urlWeather;
    }
}
