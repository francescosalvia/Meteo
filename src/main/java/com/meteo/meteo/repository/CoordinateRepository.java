package com.meteo.meteo.repository;

import com.meteo.meteo.data.Coordinate;
import com.meteo.meteo.data.CoordinateDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CoordinateRepository extends CrudRepository<CoordinateDb, Integer> {

    Optional<CoordinateDb> findCoordinateDbByIdWeather(Integer id);
    Optional<CoordinateDb> findCoordinateDbByIdForecast(Integer id);
    List<CoordinateDb>  findCoordinateDbByTipoAndLatAndLon(String tipo, float lat, float lon);
}
