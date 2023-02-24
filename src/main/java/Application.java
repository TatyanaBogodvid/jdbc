import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.net.URI;
import java.sql.*;
import java.util.Optional;

import static jdbc.ConnectionManager.*;

public class Application {
    public static void main(String[] args) {

        /*try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee WHERE id = 11");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                System.out.printf("id: %d, firstName: %s, lastName: %s, gender: %s",
                        id, firstName,lastName,gender);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        City moscow = new City(1, "Москва");
        City rostov = new City(2, "Ростов");
        City kazan = new City(3, "Казань");

        employeeDao.add(new Employee("Иван", "Иванов", "муж", 30, moscow))
                .ifPresent(employee -> System.out.println("Добавленный сотрудник: " + employee));
        Optional<Employee> employeeOptional = employeeDao.add(
                new Employee("Мария", "Иванова", "жен", 26, rostov));
                employeeOptional.ifPresent(
                        employee -> System.out.println("Добавленный сотрудник: " + employee));
        employeeDao.add(new Employee("Петр", "Гусев", "муж", 54, kazan))
                .ifPresent(employee -> System.out.println("Добавленный сотрудник: " + employee));
        employeeDao.add(new Employee("Кирилл", "Долговязов", "муж", 45))
                .ifPresent(employee -> System.out.println("Добавленный сотрудник: " + employee));

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);

        if(employeeOptional.isPresent()){
           employeeDao.findById(employeeOptional.get().getId())
                   .ifPresent(employee -> System.out.println("Найденный сотрудник: " + employee));
        }

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setAge(38);
            employee.setFirstName("Алина");
            employeeDao.update(employee)
                    .ifPresent(emp -> System.out.println("Обновленный сотрудник: " + emp));
        }

        if(employeeOptional.isPresent()){
            employeeDao.deleteById(employeeOptional.get().getId())
                    .ifPresent(emp -> System.out.println("Удаленный сотрудник: " + emp));
        }

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);

    }
}

