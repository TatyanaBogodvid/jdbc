package dao.impl;

import dao.CityDao;
import dao.EmployeeDao;
import hibernate.HibernateSessionFactoryUtil;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;



public class EmployeeDaoImpl implements EmployeeDao {

   // private final CityDao cityDao = new CityDaoImpl();

    @Override
    public Employee add(Employee employee) {
       /* if (employee.getCity() != null && !cityDao.findById(employee.getCity()).isEmpty()){
            employee.setCity(null);
        }*/
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(employee);
            Employee createdEmployee = session.get(Employee.class, createdId);
            transaction.commit();
            return createdEmployee;
        }

    }

    @Override
    public Optional<Employee> findById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return Optional.ofNullable(session.get (Employee.class, id));
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }

    @Override
    public Employee update(Employee employee) {
        /*if (employee.getCity() != null && cityDao.findById(employee.getCity()).isEmpty()){
            employee.setCity(null);
        }*/
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
           EntityTransaction entityTransaction = entityManager.getTransaction();
           entityTransaction.begin();
           Employee update = entityManager.merge(employee);
           entityTransaction.commit();
           return update;

    }

    @Override
    public Optional<Employee> delete(Employee employee) {
        Optional<Employee> employeeOptional = findById(employee.getId());
        if(employeeOptional.isPresent()) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.delete(employeeOptional.get());
                transaction.commit();
                return employeeOptional;
            }
        }
        return Optional.empty();
    }
}
