package com.meteo.meteo.repository;

import com.meteo.meteo.data.Coordinate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CoordinateRepository extends CrudRepository<Coordinate, Integer> {

    Optional<Coordinate> findCoordinateByIdWeather(Integer id);
    List<Coordinate>  findCoordinateByTipoAndLatAndLon(String tipo, float lat, float lon);
}
