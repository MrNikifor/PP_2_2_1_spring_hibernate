package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User nikita = new User("Nikita", "Osia", "osai@mail.ru");
        User petr = new User("Petr", "Petrov", "petr@mail.ru");
        User maria = new User("Masha", "Lisenkova", "maria@mail.ru");
        User alexandr = new User("Alexandr", "Vinov", "Alex@mail.ru");

        Car zapor = new Car("Zapor", 10);
        Car lada = new Car("Lada", 14);
        Car bmw = new Car("BMW", 11);
        Car toyota = new Car("Toyota", 8);

        nikita.setCar(zapor);
        petr.setCar(lada);
        maria.setCar(bmw);
        alexandr.setCar(toyota);

        userService.add(nikita);
        userService.add(petr);
        userService.add(maria);
        userService.add(alexandr);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getCar());
        }

        context.close();
    }
}
