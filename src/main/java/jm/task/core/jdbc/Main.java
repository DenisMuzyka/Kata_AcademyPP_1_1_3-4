package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("Марина", "Фролова", (byte) 18);
        userDao.saveUser("Олег", "Соколов", (byte) 22);
        userDao.saveUser("Анна", "Осипова", (byte) 19);
        userDao.saveUser("Иван", "Савельев", (byte) 26);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
