package dao;

import entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    City add (City city);

    Optional<City> findById (long id);

    List<City> findAll ();

    City update (City city);

    Optional<City> delete (City city);
}
