package com.meteo.meteo.repository;


import com.meteo.meteo.data.WeatherDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends CrudRepository<WeatherDb, Integer> {




}
