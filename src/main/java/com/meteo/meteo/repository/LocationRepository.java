package com.meteo.meteo.repository;

import com.meteo.meteo.data.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
