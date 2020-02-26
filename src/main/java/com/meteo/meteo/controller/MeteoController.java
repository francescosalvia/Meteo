package com.meteo.meteo.controller;

import com.meteo.meteo.data.Forecast;
import com.meteo.meteo.data.GenericResponse;
import com.meteo.meteo.data.Weather;
import com.meteo.meteo.service.MeteoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MeteoController {

    @Autowired
    MeteoService service;

    @GetMapping("/city")
    public ResponseEntity<GenericResponse> city(){
        Optional<Weather> result = service.getCity();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/coordinate")
    public ResponseEntity<GenericResponse> coordinate(){
        Optional<Weather> result = service.getCoordinate();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/cap")
    public ResponseEntity<GenericResponse> cap(){
        Optional<Weather> result = service.getZipCode();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/city5day")
    public ResponseEntity<GenericResponse> city5Days(){
        Optional<Forecast> result = service.get5DaysCity();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }

    @GetMapping("/coordinate5day")
    public ResponseEntity<GenericResponse> coordinate5Days(){

        Optional<Forecast> result = service.get5DaysCoordinate();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }

    @GetMapping("/cap5day")
    public ResponseEntity<GenericResponse> cap5Days(){

        Optional<Forecast> result = service.get5DaysZipCode();

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }





























}
