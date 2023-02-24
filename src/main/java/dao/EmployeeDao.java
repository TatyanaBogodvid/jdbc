package dao;

import model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> add (Employee employee);

    Optional<Employee> findById (long id);

    List<Employee> findAll ();

    Optional<Employee> update (Employee employee);

    Optional<Employee> deleteById (long id);
}
