package com.meteo.meteo;

import com.meteo.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeteoApplication {


    public static void main(String[] args) {


        SpringApplication.run(MeteoApplication.class, args);


    }
}
