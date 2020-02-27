package com.meteo.meteo.repository;

import com.meteo.meteo.data.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    Optional<Location> findLocationByIdWeather(Integer id);
    List<Location> findLocationByCitta(String citta);
    List<Location> findLocationByTipoAndZipCode(String tipo,String zipcode);
}
