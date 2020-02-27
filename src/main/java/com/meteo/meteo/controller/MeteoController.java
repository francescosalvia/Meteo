package com.meteo.meteo.controller;

import com.meteo.meteo.data.Forecast;
import com.meteo.meteo.data.GenericResponse;
import com.meteo.meteo.data.Weather;
import com.meteo.meteo.request.MeteoRequest;
import com.meteo.meteo.service.MeteoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MeteoController {

    @Autowired
    MeteoService service;

    @PostMapping("/city")
    public ResponseEntity<GenericResponse> city(@Valid @RequestBody MeteoRequest request){
        Optional<Weather> result = service.getCity(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/coordinate")
    public ResponseEntity<GenericResponse> coordinate(@Valid @RequestBody MeteoRequest request){
        Optional<Weather> result = service.getCoordinate(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/cap")
    public ResponseEntity<GenericResponse> cap(@Valid @RequestBody MeteoRequest request){
        Optional<Weather> result = service.getZipCode(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }

    }

    @GetMapping("/city5day")
    public ResponseEntity<GenericResponse> city5Days(@Valid @RequestBody MeteoRequest request){
        Optional<Forecast> result = service.get5DaysCity(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }

    @GetMapping("/coordinate5day")
    public ResponseEntity<GenericResponse> coordinate5Days(@Valid @RequestBody MeteoRequest request){

        Optional<Forecast> result = service.get5DaysCoordinate(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }

    @GetMapping("/cap5day")
    public ResponseEntity<GenericResponse> cap5Days(@Valid @RequestBody MeteoRequest request){

        Optional<Forecast> result = service.get5DaysZipCode(request);

        if (result.isPresent()){
            return ResponseEntity.ok(new GenericResponse("Servizio meteo disponibile!", result.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(GenericResponse.failed("Servizio meteo non disponibile"));
        }


    }





























}
