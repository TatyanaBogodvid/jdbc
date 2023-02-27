import dao.CityDao;
import dao.EmployeeDao;
import dao.impl.CityDaoImpl;
import dao.impl.EmployeeDaoImpl;
import entity.City;
import entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CityDao cityDao = new CityDaoImpl();

        int n = 5;
        City city = new City("Норильск");
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++){
            employees.add(new Employee(
                    "Имя "+(i+1),
                    "Фамилия "+(i+1),
                    "муж",
                    30+i,
                    city
                )
            );
        }
        city.setEmployees(employees);

        cityDao.add(city);

        employeeDao.findAll().forEach(System.out::println);
        cityDao.delete(city);
        employeeDao.findAll().forEach(System.out::println);
    }
}

