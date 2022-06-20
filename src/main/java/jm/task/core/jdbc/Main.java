package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();

        User[] users = {new User("Andrew", "K", (byte) 30),
                new User("Nastya", "K", (byte) 28),
                new User("Alisa", "K", (byte) 3),
                new User("Alexsey", "M", (byte) 29)};
        for (User user :
                users) {
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
        }

<<<<<<< HEAD
        System.out.println();

=======
>>>>>>> 55d6e975df721b6788066f631b9e7de1d7029ba5
        List<User> usersFromDB = service.getAllUsers();
        usersFromDB.forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
