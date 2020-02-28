package com.meteo.meteo.repository;

import com.meteo.meteo.data.ForecastListInfoDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastInfoListRepository extends CrudRepository<ForecastListInfoDb, Integer> {

    List<ForecastListInfoDb> findForecastListInfoDbByIdForecast(Integer id);


}
