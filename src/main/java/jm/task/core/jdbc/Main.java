package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("������", "�������", (byte) 18);
        userDao.saveUser("����", "�������", (byte) 22);
        userDao.saveUser("����", "�������", (byte) 19);
        userDao.saveUser("����", "��������", (byte) 26);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
