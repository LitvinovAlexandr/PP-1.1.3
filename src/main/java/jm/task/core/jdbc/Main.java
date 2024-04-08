package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

//        userService.dropUsersTable();

        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 30);
        userService.saveUser("Петр", "Петров", (byte) 26);
        userService.saveUser("Света", "Светикова", (byte) 28);
        userService.saveUser("Тимофей", "Тимохин", (byte) 20);

        userService.removeUserById(2);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
//        System.out.println(userService.getAllUsers());

    }
}
