package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("������", "�������", (byte) 18);
        userDao.saveUser("����", "�������", (byte) 22);
        userDao.saveUser("����", "�������", (byte) 19);
        userDao.saveUser("����", "��������", (byte) 26);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.getConnection().close();
    }
}
