package dao.impl;

import dao.CityDao;
import hibernate.HibernateSessionFactoryUtil;
import entity.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDao {

    @Override
    public City add(City city) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(city);
            City createdCity = session.get(City.class, createdId);
            transaction.commit();
            return createdCity;
        }
    }

    @Override
    public Optional<City> findById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM City", City.class).list();
        }
    }

    @Override
    public City update(City city) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        City update = entityManager.merge(city);
        entityTransaction.commit();
        return update;
    }

    @Override
    public Optional<City> delete(City city) {
        Optional<City> cityOptional = findById(city.getCityId());
        if(cityOptional.isPresent()) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.delete(cityOptional.get());
                transaction.commit();
                return cityOptional;
            }
        }
        return Optional.empty();
    }
}
