package com.meteo.meteo;

import com.meteo.meteo.service.Servizio;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MeteoApplication {


    public static void main(String[] args) {


        SpringApplication.run(MeteoApplication.class, args);

        Servizio servizio = new Servizio();


        servizio.getCity("London", "uk", "GB");

        servizio.getCoordinate("139","35");

        servizio.getZipCode("46047","it");

        servizio.get5DaysCity("Milan", "it", "IT");

        servizio.get5DaysCoordinate("139","35");

        servizio.get5DaysZipCode("46047","it");

    }

}
