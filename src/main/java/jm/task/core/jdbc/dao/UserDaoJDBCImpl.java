package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(30) NOT NULL," +
            "lastName VARCHAR(50) NOT NULL," +
            "age INT NOT NULL CHECK (age > '0'))";
    private static final String DROP = "DROP TABLE IF EXISTS users";
    private static final String INSERT = "INSERT INTO users (name, lastname, age) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String TRUNCATE = "TRUNCATE TABLE users";
    private static final String GET_ALL = "SELECT * FROM users";
    PreparedStatement preparedStatement = null;
    Statement statement;

    {
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            statement.executeUpdate(CREATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try {
            statement.executeUpdate(DROP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            preparedStatement = Util.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем " + name + " добавлен в БД");
    }

    public void removeUserById(long id) {
        try {
            preparedStatement = Util.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                User user = new User();
                list.add(user);
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate(TRUNCATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
