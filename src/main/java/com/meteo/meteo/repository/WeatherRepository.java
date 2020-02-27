package com.meteo.meteo.repository;


import com.meteo.meteo.data.Weather;
import com.meteo.meteo.data.WeatherDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WeatherRepository extends CrudRepository<WeatherDb, Integer> {

    List<WeatherDb> findWeatherDbByTipoMetodo(String tipoMetodo);
    List<WeatherDb> findWeatherDbByTipoMetodoAndId(String tipoMetodo,Integer id);
}
