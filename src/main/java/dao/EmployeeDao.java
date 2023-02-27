package dao;

import entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Employee add (Employee employee);

    Optional<Employee> findById (long id);

    List<Employee> findAll ();

    Employee update (Employee employee);

    Optional<Employee> delete (Employee employee);
}
