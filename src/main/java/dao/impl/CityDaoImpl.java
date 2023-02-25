package dao.impl;

import dao.CityDao;
import hibernate.HibernateSessionFactoryUtil;
import jdbc.ConnectionManager;
import model.City;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class CityDaoImpl implements CityDao {

    @Override
    public Optional<City> findById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        }
    }
}
