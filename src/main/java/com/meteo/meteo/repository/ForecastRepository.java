package com.meteo.meteo.repository;

import com.meteo.meteo.data.ForecastDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastRepository extends CrudRepository<ForecastDb, Integer> {

    List<ForecastDb> findForecastDbByTipoMetodoAndId(String tipo, Integer id);

}
