package com.meteo.meteo;

import com.meteo.meteo.service.MeteoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeteoApplication {


    public static void main(String[] args) {


        SpringApplication.run(MeteoApplication.class, args);

        MeteoService servizio = new MeteoService();


        servizio.getCity();

        servizio.getCoordinate();

        servizio.getZipCode();

        servizio.get5DaysCity();

        servizio.get5DaysCoordinate();

        servizio.get5DaysZipCode();

    }

}
