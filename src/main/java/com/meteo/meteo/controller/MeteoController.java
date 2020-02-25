package com.meteo.meteo.controller;

import com.meteo.meteo.data.Weather;
import com.meteo.meteo.service.MeteoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MeteoController {

    @Autowired
    MeteoService service;

    @GetMapping("/city")
    public Object city(){
        Optional<Weather> result = service.getCity();

        if (result.isPresent()){
            return result.get();
        } else {
            return "Servizio meteo non disponibile";
        }

    }

    @GetMapping("/coordinate")
    public String coordinate(){
        String result = service.getCoordinate();

        if (result != null){

            return result;
        } else {
            return "Servizio meteo non disponibile";
        }

    }

    @GetMapping("/cap")
    public String cap(){
        String result = service.getZipCode();

        if (result != null){

            return result;
        } else {
            return "Servizio meteo non disponibile";
        }

    }

    @GetMapping("/city5day")
    public String city5Days(){
        String result = service.get5DaysCity();

        if (result != null){
            return result;
        } else {
            return "Servizio meteo non disponibile";
        }

    }

    @GetMapping("/coordinate5day")
    public String coordinate5Days(){

        String result = service.get5DaysCoordinate();
        if (result != null){
            return result;
        } else {
            return "Servizio meteo non disponibile";
        }

    }

    @GetMapping("/cap5day")
    public String cap5Days(){

        String result = service.get5DaysZipCode();
        if (result != null){
            return result;
        } else {
            return "Servizio meteo non disponibile";
        }

    }





























}
