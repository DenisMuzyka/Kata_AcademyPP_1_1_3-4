package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        Util.getSessionFactory();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Марина", "Фролова", (byte) 18);
        userService.saveUser("Олег", "Соколов", (byte) 22);
        userService.saveUser("Анна", "Осипова", (byte) 19);
        userService.saveUser("Иван", "Савельев", (byte) 26);

        userService.removeUserById(1);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
