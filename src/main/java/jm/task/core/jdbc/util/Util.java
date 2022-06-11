package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties prop = new Properties();

                prop.setProperty("hibernate.connection.url", URL);
                prop.setProperty("hibernate.connection.username", USERNAME);
                prop.setProperty("hibernate.connection.password", PASSWORD);
                prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

                sessionFactory = configuration.addProperties(prop).buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
