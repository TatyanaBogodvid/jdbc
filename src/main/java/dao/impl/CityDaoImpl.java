package dao.impl;

import dao.CityDao;
import jdbc.ConnectionManager;
import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class CityDaoImpl implements CityDao {

    private static final String FIND_BY_ID = "SELECT * FROM city WHERE city_id = ?";
    @Override
    public Optional<City> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Optional.of(
                 new City (
                       resultSet.getLong("city_id"),
                       resultSet.getString("cyti_name")
                 )
               );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
