package com.meteo.meteo.repository;

import com.meteo.meteo.data.Location;
import com.meteo.meteo.data.LocationDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<LocationDb, Integer> {

    Optional<LocationDb> findLocationDbByIdWeather(Integer id);
    Optional<LocationDb> findLocationDbByIdForecast(Integer id);
    List<LocationDb> findLocationDbByCittaAndTipo(String citta,String tipo);
    List<LocationDb> findLocationDbByTipoAndZipCode(String tipo,String zipcode);
}
