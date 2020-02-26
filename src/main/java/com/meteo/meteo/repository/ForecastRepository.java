package com.meteo.meteo.repository;

import com.meteo.meteo.data.Forecast;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast, Integer> {


}
