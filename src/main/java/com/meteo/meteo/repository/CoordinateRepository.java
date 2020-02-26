package com.meteo.meteo.repository;

import com.meteo.meteo.data.Coordinate;
import org.springframework.data.repository.CrudRepository;

public interface CoordinateRepository extends CrudRepository<Coordinate, Integer> {
}
