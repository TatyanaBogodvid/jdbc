import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

public class Application {
    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        City moscow = new City(1, "Москва");
        City rostov = new City(2, "Ростов");
        City kazan = new City(3, "Казань");

        Employee ivan = employeeDao.add(new Employee("Иван", "Иванов", "муж", 30, 1L));
        System.out.println("Добавленный сотрудник: " + ivan);

        Employee maria = employeeDao.add(new Employee("Мария", "Иванова", "жен", 26, 2L));
        System.out.println("Добавленный сотрудник: " + maria);

        Employee petr = employeeDao.add(new Employee("Петр", "Гусев", "муж", 54, 3L));
        System.out.println("Добавленный сотрудник: " + petr);

        Employee kirill = employeeDao.add(new Employee("Кирилл", "Долговязов", "муж", 45));
        System.out.println("Добавленный сотрудник: " + kirill);

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);


        employeeDao.findById(petr.getId())
                .ifPresent(employee -> System.out.println("Найденный сотрудник: " + employee));

        petr.setAge(38);
        petr.setFirstName("Алина");
        petr.setCity(1L);
        petr = employeeDao.update(petr);
        System.out.println("Обновленный сотрудник: " + petr);

        employeeDao.delete(petr)
                .ifPresent(emp -> System.out.println("Удаленный сотрудник: " + emp));

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);

    }
}

